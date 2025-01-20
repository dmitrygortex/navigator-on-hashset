import Algorithms.HashSetDM;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HashSetDM<Route> routeHashSet = new HashSetDM<>();
    private static final NavigatorImpl navigator = new NavigatorImpl(routeHashSet);

    public static void main(String[] args) {
        addInitialRoutes();

        int action;
        do {
            printMenu();
            action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1 -> addRoute();
                case 2 -> removeRoute();
                case 3 -> listAllRoutes();
                case 4 -> searchRoutes();
                case 5 -> getTop5Routes();
                case 6 -> chooseRoute();
                case 7 -> getFavoriteRoutes();
                case 8 -> checkIfRouteExists();
                case 9 -> exit();
                default -> System.out.println("Неверный ввод. Попробуйте снова.");
            }
        } while (action != 9);
    }

    private static void addInitialRoutes() {
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

        System.out.println("Изначальные маршруты добавлены!\n");
    }

    private static void printMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. Добавить маршрут");
        System.out.println("2. Удалить маршрут");
        System.out.println("3. Показать все маршруты");
        System.out.println("4. Найти маршруты по пунктам отправления и назначения");
        System.out.println("5. Показать топ-5 маршрутов по популярности");
        System.out.println("6. Выбрать маршрут");
        System.out.println("7. Показать избранные маршруты для определенного пункта");
        System.out.println("8. Проверить, существует ли маршрут");
        System.out.println("9. Выйти");
        System.out.print("Ваш выбор: ");
    }

    private static void addRoute() {
        System.out.print("Введите расстояние: ");
        double distance = scanner.nextDouble();

        System.out.print("Введите популярность: ");
        int popularity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Это избранный маршрут? (да/нет): ");
        boolean isFavorite = scanner.nextLine().equalsIgnoreCase("да");

        System.out.print("Введите пункты маршрута через запятую: ");
        String[] points = scanner.nextLine().split(", ");

        Route newRoute = new Route(distance, popularity, isFavorite, Arrays.asList(points));
        navigator.addRoute(newRoute);

        System.out.println("Маршрут добавлен!");
    }

    private static void removeRoute() {
        System.out.print("Введите ID маршрута для удаления: ");
        String routeId = scanner.nextLine();
        navigator.removeRoute(routeId);
        System.out.println("Маршрут удален, если он существовал.");
    }

    private static void listAllRoutes() {
        System.out.println("\nСписок всех маршрутов:");
        for (Route route : routeHashSet.iterator()) {
            System.out.println(route);
        }
    }

    private static void searchRoutes() {
        System.out.print("Введите начальный пункт: ");
        String startPoint = scanner.nextLine();

        System.out.print("Введите конечный пункт: ");
        String endPoint = scanner.nextLine();

        System.out.println("Результаты поиска:");
        for (Route route : navigator.searchRoutes(startPoint, endPoint)) {
            System.out.println(route);
        }
    }

    private static void getTop5Routes() {
        System.out.println("\nТоп-5 маршрутов:");
        for (Route route : navigator.getTop5Routes()) {
            System.out.println(route);
        }
    }

    private static void chooseRoute() {
        System.out.print("Введите ID маршрута: ");
        String routeId = scanner.nextLine();
        navigator.chooseRoute(routeId);
        System.out.println("Маршрут выбран!");
    }

    private static void getFavoriteRoutes() {
        System.out.print("Введите пункт назначения: ");
        String point = scanner.nextLine();
        System.out.println("Избранные маршруты:");
        for (Route route : navigator.getFavoriteRoutes(point)) {
            System.out.println(route);
        }
    }

    private static void checkIfRouteExists() {
        System.out.print("Введите ID маршрута: ");
        String routeId = scanner.nextLine();
        boolean exists = navigator.contains(navigator.getRoute(routeId));
        System.out.println(exists ? "Маршрут существует!" : "Маршрут не найден.");
    }

    private static void exit() {
        System.out.println("Завершение работы. До свидания!");
    }
}
