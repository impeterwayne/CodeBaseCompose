package com.genesys.core.ui.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector

/**
 * A builder for creating a carousel with no snap behavior using Epoxy's DSL.
 * This allows building a carousel in a type-safe way with the Epoxy DSL.
 *
 * Example usage:
 * ```
 * carouselNoSnapBuilder {
 *   id("myCarousel")
 *   padding(Carousel.Padding.dp(16, 0, 16, 0, 8))
 *   models = listOf(
 *     MyItemModel_()
 *       .id("item1")
 *       .title("Item 1"),
 *     MyItemModel_()
 *       .id("item2")
 *       .title("Item 2")
 *   )
 * }
 * ```
 */
fun ModelCollector.carouselNoSnapBuilder(builder: EpoxyCarouselNoSnapBuilder.() -> Unit): CarouselNoSnapModel_ {
    val carouselBuilder = EpoxyCarouselNoSnapBuilder().apply { builder() }
    add(carouselBuilder.carouselNoSnapModel)
    return carouselBuilder.carouselNoSnapModel
}

/**
 * Builder class for creating a carousel with no snap behavior.
 * Implements the CarouselNoSnapModelBuilder interface to provide a type-safe DSL.
 */
class EpoxyCarouselNoSnapBuilder(
    internal val carouselNoSnapModel: CarouselNoSnapModel_ = CarouselNoSnapModel_()
) : ModelCollector, CarouselNoSnapModelBuilder by carouselNoSnapModel {
    private val models = mutableListOf<EpoxyModel<*>>()

    override fun add(model: EpoxyModel<*>) {
        models.add(model)
        // Set models list every time a model is added so that it can run debug validations to
        // ensure it is still valid to mutate the carousel model.
        carouselNoSnapModel.models(models)
    }
}
