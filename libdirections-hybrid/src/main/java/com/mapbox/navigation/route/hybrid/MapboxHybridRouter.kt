package com.mapbox.navigation.route.hybrid

import com.mapbox.annotation.navigation.module.MapboxNavigationModule
import com.mapbox.annotation.navigation.module.MapboxNavigationModuleType
import com.mapbox.geojson.Point
import com.mapbox.navigation.base.route.model.Route
import com.mapbox.navigation.route.offboard.MapboxOffboardRouter
import com.mapbox.navigation.route.onboard.MapboxOnboardRouter

@MapboxNavigationModule(MapboxNavigationModuleType.HybridRouter, skipConfiguration = true)
class MapboxHybridRouter(
    private val onboardRouter: Router,
    private val offboardRouter: Router
) : Router {

    override fun getRoute(origin: Point, waypoints: List<Point>, callback: (route: Route) -> Unit) {
        TODO("not implemented")
    }

    override fun cancel() {
        onboardRouter.cancel()
        offboardRouter.cancel()
    }
}