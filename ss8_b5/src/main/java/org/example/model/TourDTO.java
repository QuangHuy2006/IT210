package org.example.model;


import jakarta.validation.Valid;


import jakarta.validation.constraints.*;
import org.example.validator.ValidPrice;
import org.example.validator.ValidTourDate;

import java.time.LocalDate;

@ValidTourDate
@ValidPrice
public class TourDTO {

    @NotBlank
    @Pattern(regexp = "^(VN|INT)_\\d{5}$")
    private String tourCode;

    @Min(1)
    private double adultPrice;

    @Min(1)
    private double childPrice;

    @FutureOrPresent
    private LocalDate startDate;

    private LocalDate endDate;

    public TourDTO() {}


    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTourCode() {
        return tourCode;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}