package com.abduladf.satusiaga.data.mapper

import com.abduladf.satusiaga.data.model.DisasterReportsResponse
import com.abduladf.satusiaga.domain.model.DisasterItem

fun DisasterReportsResponse.Result.Objects.Output.Geometry.toModel() = DisasterItem(
    imageUrl = properties?.imageUrl.orEmpty(),
    title = properties?.title?: "<No Title>",
    subtitle = properties?.text.orEmpty()
)