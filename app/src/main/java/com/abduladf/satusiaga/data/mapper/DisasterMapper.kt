package com.abduladf.satusiaga.data.mapper

import com.abduladf.satusiaga.data.model.DisasterReportsResponse
import com.abduladf.satusiaga.domain.model.DisasterItem
import com.abduladf.satusiaga.utils.SearchItemsUtil
import com.abduladf.satusiaga.utils.SearchItemsUtil.getKeyBySearchItemValue

fun DisasterReportsResponse.Result.Objects.Output.Geometry.toModel() = DisasterItem(
    imageUrl = properties?.imageUrl.orEmpty(),
    title = properties?.title?:
        (properties?.disasterType?.replaceFirstChar(Char::uppercase) + " di " + getKeyBySearchItemValue(properties?.tags?.instanceRegionCode)),
    subtitle = properties?.text.orEmpty(),
    coordinates = coordinates
)