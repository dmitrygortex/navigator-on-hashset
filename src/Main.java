import Algorithms.HashSetDM;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashSetDM<Route> routeHashSet = new HashSetDM<>();

        NavigatorImpl navigator = new NavigatorImpl(routeHashSet);

        Route route1 = new Route(15.5, 100, true, Arrays.asList("Москва", "Санкт-Петербург"));
        Route route2 = new Route(8.3, 50, false, Arrays.asList("Казань", "Уфа"));
        Route route3 = new Route(20.0, 120, true, Arrays.asList("Сочи", "Краснодар"));
        Route route4 = new Route(5.0, 30, false, Arrays.asList("Новосибирск", "Барнаул"));
        Route route5 = new Route(25.0, 200, true, Arrays.asList("Екатеринбург", "Челябинск"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);
        navigator.addRoute(route5);

        // добавить маршрут, который уже существует
        navigator.addRoute(route1);

        System.out.println("\nВсе маршруты:");
        for (Route route : routeHashSet.iterator()) {
            System.out.println("ID: " + route.getId() + ", Дистанция: " + route.getDistance() +
                    ", Популярность: " + route.getPopularity() + ", Избранное: " + route.isFavorite());
        }

        System.out.println("\nСодержит ли маршрут route2? " + navigator.contains(route2));

        navigator.removeRoute(route2.getId());

        navigator.chooseRoute(route3.getId());

        System.out.println("\nМаршруты из 'Москва' в 'Санкт-Петербург':");
        for (Route route : navigator.searchRoutes("Москва", "Санкт-Петербург")) {
            System.out.println(route.getId());
        }

        System.out.println("\nТоп-5 маршрутов:");
        for (Route route : navigator.getTop5Routes()) {
            System.out.println("ID: " + route.getId() + ", Популярность: " + route.getPopularity());
        }

        System.out.println("\nИзбранные маршруты с точкой 'Санкт-Петербург':");
        for (Route route : navigator.getFavoriteRoutes("Санкт-Петербург")) {
            System.out.println("ID: " + route.getId() + ", Избранное: " + route.isFavorite());
        }
    }
}
