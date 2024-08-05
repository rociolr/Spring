package com.atsistemas.apireservas.utilities.specifications;

import com.atsistemas.apireservas.entities.Availability;
import com.atsistemas.apireservas.utilities.filters.AvailabilitiesFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AvailabilitiesSpecifications {
    public static Specification<Availability> getSpecification(AvailabilitiesFilter availabilitiesFilter) {
        Specification<Availability> spec = Specification.where(null);
        //DatesFilter
        spec = spec.and(filterAvailabilitiesBetweenTwoDates(availabilitiesFilter.getDateFrom(), availabilitiesFilter.getDateTo()));
        if (availabilitiesFilter.getCategory() != null) {
            //CategoryFilter
            spec = spec.and(filterByCategory(availabilitiesFilter.getCategory()));
        }
        if (availabilitiesFilter.getHotelName() != null) {
            //HotelNameFilter
            spec = spec.and(filterByHotelName(availabilitiesFilter.getHotelName()));
        }
        return spec;
    }

    private static Specification<Availability> filterByHotelName(String hotelName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("name"), hotelName));
    }

    private static Specification<Availability> filterByCategory(Integer category) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("category"), category));
    }

    private static Specification<Availability> filterAvailabilitiesBetweenTwoDates(LocalDate dateFrom, LocalDate dateTo) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("date"), dateFrom, dateTo));
    }
}
