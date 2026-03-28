package com.genesys.feature.template.main.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.genesys.core.ui.epoxy.BaseEpoxyViewBindingHolder
import com.genesys.feature.template.R
import com.genesys.feature.template.databinding.ItemTemplateCollectionHeaderBinding

@EpoxyModelClass
abstract class TemplateCollectionHeaderModel :
    BaseEpoxyViewBindingHolder<ItemTemplateCollectionHeaderBinding>() {

    @EpoxyAttribute
    open var collectionName: String = ""

    override fun getDefaultLayout(): Int = R.layout.item_template_collection_header

    override fun ItemTemplateCollectionHeaderBinding.bind() {
        tvCollectionName.text = collectionName
    }
}
