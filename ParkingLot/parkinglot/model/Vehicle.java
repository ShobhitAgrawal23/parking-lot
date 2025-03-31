package parkinglot.model;

public class Vehicle {
    private String number;
    private VehicleType type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Vehicle(Builder builder) {
        this.number = builder.number;
        this.type = builder.type;
    }

    public static class Builder {
        private String number;
        private VehicleType type;

        public static Builder newInstance()
        {
            return new Builder();
        }

        private Builder() {}

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setType(VehicleType type) {
            this.type = type;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }

    }
}
