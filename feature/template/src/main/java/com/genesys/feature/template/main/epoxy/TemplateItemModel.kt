package com.genesys.feature.template.main.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.genesys.core.common.extension.loadImageWithGlide
import com.genesys.core.model.template.Template
import com.genesys.core.ui.epoxy.BaseEpoxyViewBindingHolder
import com.genesys.feature.template.R
import com.genesys.feature.template.databinding.ItemTemplateBinding
import timber.log.Timber

@EpoxyModelClass
abstract class TemplateItemModel :
    BaseEpoxyViewBindingHolder<ItemTemplateBinding>() {

    @EpoxyAttribute
    open var template: Template = Template()

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var onItemClick: (() -> Unit)? = null

    override fun getDefaultLayout(): Int = R.layout.item_template

    override fun ItemTemplateBinding.bind() {
        val imageUrl = BASE_URL + "content"+ template.thumbnail
        Timber.tag("URL").d("$imageUrl")
        imgThumbnail.loadImageWithGlide(imageUrl)
        tvTemplateName.text = template.name
        ivPremiumBadge.visibility = if (template.premium) View.VISIBLE else View.GONE
        root.setOnClickListener { onItemClick?.invoke() }
    }

    override fun ItemTemplateBinding.unbind() {
        root.setOnClickListener(null)
    }

    companion object {
        private const val BASE_URL = "https://ai-service.backendvn.com/"
    }
}
