import Algorithms.HashSetDM;

import java.util.ArrayList;

public class NavigatorImpl implements Navigator {

    private HashSetDM<Route> routes;

    public NavigatorImpl(HashSetDM<Route> routes) {
        this.routes = routes;
    }

    @Override
    public void addRoute(Route route) {
        if (routes.contains(route)) {
            System.out.println("Маршрут " + route.getId() + " уже существует, добавление было отменено");
        }
        else {
            routes.add(route);
            System.out.print("Маршрут " + route.getId() + " добавлен\n");
        }
    }

    @Override
    public void removeRoute(String routeId) {
        Route route = getRoute(routeId);
        if (route!= null) {
            routes.remove(route);
            System.out.println("Маршрут " + route.getId() + " удалось удалить");
        }
        else {
            System.out.println("Маршрут с id " + routeId + " не найден, удаление не выполнено");
        }
    }

    @Override
    public boolean contains(Route route) {
        return routes.contains(route);
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        for (var route : routes.iterator()) {
            if (route.getId().equals(routeId)) {
                return route;
            }
        }
        return null;
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = getRoute(routeId);
        if (route!= null) {
            System.out.println("Маршрут " + route.getId() + " выбран");
            route.increasePopularity();
        }
        else {
            System.out.println("Маршрут " + routeId + " не был найден");
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
//        var routesFavorite = new ArrayList<Route>();
//        var routesOther = new ArrayList<Route>();
//        for (var route : routes.iterator()) {
//            var locationList = route.getLocationPoints();
//            if (locationList.size() >= 2 &&
//                locationList.getFirst().equals(startPoint) &&
//                locationList.getLast().equals(endPoint)
//            ) {
//                if (route.isFavorite()){
//                    routesFavorite.add(route);
//                }
//                else {
//                    routesOther.add(route);
//                }
//            }
//        }
        var result = new ArrayList<Route>();
        for (var route : routes.iterator()) {
            var points = route.getLocationPoints();
            if (points.size() >= 2 &&
                    points.getFirst().equals(startPoint) &&
                    points.getLast().equals(endPoint)) {
                result.add(route);
            }
        }
        result.sort((r1, r2) ->
        {
            if (r1.isFavorite() != r2.isFavorite()) {
                return r1.isFavorite() ? -1 : 1;
            }
            int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
            return distanceComparison == 0
                    ? Integer.compare(r2.getPopularity(), r1.getPopularity())
                    : distanceComparison;
        });
        return result;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        var result = new ArrayList<Route>();
        for (var route : routes.iterator()) {
            var points = route.getLocationPoints();
            if (route.isFavorite() &&
                    points.contains(destinationPoint) &&
                    !points.getFirst().equals(destinationPoint)) {
                result.add(route);
            }
        }
        result.sort((r1, r2) -> {
            int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
            return distanceComparison == 0
                    ? Integer.compare(r2.getPopularity(), r1.getPopularity())
                    : distanceComparison;
        });
        return result;
    }

    @Override
    public Iterable<Route> getTop5Routes() {
        var sortedRoutes = new ArrayList<Route>();
        for (var route : routes.iterator()) {
            sortedRoutes.add(route);
        }
        sortedRoutes.sort((r1, r2) -> {
            int popularityComparison = Integer.compare(r2.getPopularity(), r1.getPopularity());
            if (popularityComparison != 0) {
                return popularityComparison;
            }
            int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
            if (distanceComparison != 0) {
                return distanceComparison;
            }
            return Integer.compare(r1.getLocationPoints().size(), r2.getLocationPoints().size());
        });
        return sortedRoutes.subList(0, Math.min(5, sortedRoutes.size()));
    }
}
