package com.genesys.feature.template.main

import android.view.View
import androidx.fragment.app.viewModels
import com.airbnb.epoxy.Carousel
import com.genesys.core.common.base.BaseFragment
import com.genesys.core.common.extension.collectRepeatOnLifecycle
import com.genesys.core.model.template.TemplateCollections
import com.genesys.core.ui.epoxy.carouselNoSnapBuilder
import com.genesys.feature.template.databinding.FragmentMainBinding
import com.genesys.feature.template.main.epoxy.templateCollectionHeader
import com.genesys.feature.template.main.epoxy.templateItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    private var isLoading = false
    private var errorMessage: String? = null

    override fun initViews() {
        mainViewModel.onEvent(MainViewModel.MainEvent.LoadTemplates)
    }

    override fun initObservers() {
        viewLifecycleOwner.collectRepeatOnLifecycle(mainViewModel.templateCollections) { collections ->
            setupEpoxy(collections)
        }

        viewLifecycleOwner.collectRepeatOnLifecycle(mainViewModel.isLoading) { loading ->
            isLoading = loading
            viewBinding.progressLoading.visibility = if (loading) View.VISIBLE else View.GONE
            viewBinding.rcvTemplates.visibility = if (loading) View.GONE else View.VISIBLE
        }

        viewLifecycleOwner.collectRepeatOnLifecycle(mainViewModel.errorMessage) { error ->
            errorMessage = error
            viewBinding.layoutError.visibility = if (error != null) View.VISIBLE else View.GONE
            viewBinding.tvErrorMessage.text = error
        }
    }

    override fun initListeners() {
        viewBinding.btnRetry.setOnClickListener {
            mainViewModel.onEvent(MainViewModel.MainEvent.LoadTemplates)
        }
    }

    private fun setupEpoxy(collections: List<TemplateCollections>) {
        viewBinding.rcvTemplates.withModels {
            collections.forEach { collection ->
                // Section Header
                templateCollectionHeader {
                    id("header_${collection.id}")
                    collectionName(collection.name)
                }

                // Horizontal carousel of template items
                carouselNoSnapBuilder {
                    id("carousel_${collection.id}")
                    padding(Carousel.Padding.dp(16, 0, 16, 0, 8))

                    collection.templates.forEach { template ->
                        templateItem {
                            id("template_${template.id}")
                            template(template)
                            onItemClick {
                                mainViewModel.onEvent(
                                    MainViewModel.MainEvent.OnTemplateClicked(template)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
