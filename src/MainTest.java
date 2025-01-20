import Algorithms.HashSetDM;
import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        HashSetDM<Route> routeHashSet = new HashSetDM<>();

        NavigatorImpl navigator = new NavigatorImpl(routeHashSet);

        Route route1 = new Route(1000, 1055, true, Arrays.asList("Москва", "Тверь", "Великий Новгород", "Санкт-Петербург"));
        Route route2 = new Route(705, 75, false, Arrays.asList("Екатеринбург", "Тюмень", "Омск", "Владивосток"));
        Route route3 = new Route(505.5, 50, true, Arrays.asList("Сочи", "Город1", "Краснодар"));
        Route route4 = new Route(500, 49, false, Arrays.asList("Уфа", "Челябинск", "Екатеринбург"));
        Route route5 = new Route(405.5, 48, true, Arrays.asList("Екатеринбург", "Курган", "Челябинск"));
        Route route6 = new Route(350.5, 47, false, Arrays.asList("Владивосток", "Город2", "Хабаровск"));
        Route route7 = new Route(350, 46, true, Arrays.asList("Омск", "Город3", "Тюмень"));
        Route route8 = new Route(205, 45, false, Arrays.asList("Самара", "Город1", "Тюмень"));
        Route route9 = new Route(150, 44, true, Arrays.asList("Пермь", "Город1", "Екатеринбург"));
        Route route10 = new Route(100, 43, false, Arrays.asList("Тюмень", "Город1", "Екатеринбург"));

        Route route11 = new Route(990, 1050, true, Arrays.asList("Москва", "Город99", "Город4", "Санкт-Петербург"));
        Route route12 = new Route(990, 1050, true, Arrays.asList("Москва", "Город99", "Город4", "Санкт-Петербург"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);
        navigator.addRoute(route5);
        navigator.addRoute(route6);
        navigator.addRoute(route7);
        navigator.addRoute(route8);
        navigator.addRoute(route9);
        navigator.addRoute(route10);
        navigator.addRoute(route1);
        navigator.addRoute(route11);
        navigator.addRoute(route12);

        System.out.println("\nВсе маршруты:");
        for (Route route : routeHashSet.iterator()) {
            System.out.println("ID: " + route.getId() + ", Дистанция: " + route.getDistance() +
                    ", Популярность: " + route.getPopularity() + ", Избранное: " + route.isFavorite());
        }

        System.out.println("\nСодержит ли маршрут route2? " + navigator.contains(route2));

        navigator.removeRoute(route2.getId());


        System.out.println("\nМаршруты из 'Москва' в 'Санкт-Петербург':");
        for (Route route : navigator.searchRoutes("Москва", "Санкт-Петербург")) {
            System.out.println(route.getId());
        }

        System.out.println("\nТоп-5:");
        for (Route route : navigator.getTop5Routes()) {
            System.out.println("ID: " + route.getId() + ", Популярность: " + route.getPopularity());
        }

        System.out.println();
        navigator.chooseRoute(route4.getId());
        navigator.chooseRoute(route4.getId());
        navigator.chooseRoute(route4.getId());


        System.out.println("\nТоп 5:");
        for (Route route : navigator.getTop5Routes()) {
            System.out.println("ID: " + route.getId() + ", Популярность: " + route.getPopularity());
        }

        System.out.println("\nИзбранные в Санкт-Петербург:");
        for (Route route : navigator.getFavoriteRoutes("Санкт-Петербург")) {
            System.out.println("ID: " + route.getId() + ", Избранное: " + route.isFavorite());
        }
    }
}