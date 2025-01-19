import java.util.ArrayList;
import java.util.List;

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
            System.out.print("Маршрут " + route.getId() + " добавлен");
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
        var routesFavorite = new ArrayList<Route>();
        var routesOther = new ArrayList<Route>();
        for (var route : routes.iterator()) {
            var locationList = route.getLocationPoints();
            if (locationList.size() >= 2 &&
                locationList.getFirst().equals(startPoint) &&
                locationList.getLast().equals(endPoint)
            ) {
                if (route.isFavorite()){
                    routesFavorite.add(route);
                }
                else {
                    routesOther.add(route);
                }
            }
        }

        // TODO: доделать
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        return null;
    }

    @Override
    public Iterable<Route> getTop5Routes() {
        return null;
    }
}
