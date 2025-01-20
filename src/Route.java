import Helpers.IdGenerator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Route {

    //•	Id – String
    //•	Distance – double
    //•	Popularity – int
    //•	IsFavorite – bool
    //•	LocationPoints – список строк (Точка, для упрощения, просто названия городов)

    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private List<String> locationPoints;

    public Route(double distance, int popularity, boolean isFavorite, List<String> locationPoints) {
        this.id = IdGenerator.generateId();
        // если можно использовать то лучше этот, если нет то мой
        //this.id = UUID.randomUUID().toString();
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public String getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    private void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    private void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    private void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getLocationPoints() {
        return locationPoints;
    }

    private void setLocationPoints(List<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    public void increasePopularity() {
        setPopularity(popularity + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(distance, route.distance) == 0 &&
                popularity == route.popularity &&
                isFavorite == route.isFavorite &&
                //Objects.equals(id, route.id) &&
                Objects.equals(locationPoints.getFirst(), route.locationPoints.getFirst()) &&
                Objects.equals(locationPoints.getLast(), route.locationPoints.getLast())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                distance,
                popularity,
                isFavorite,
                locationPoints.getFirst(),
                locationPoints.getLast());
    }
}
