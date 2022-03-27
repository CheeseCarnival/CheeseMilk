package com.cheeseocean.common.page;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * @author xxxcrel
 * Created on 2022/3/27
 */
public class Sort {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public static class Order implements Serializable {

        private static final long serialVersionUID = 1522511010900108987L;
        private Direction direction;
        private String property;
        private boolean ignoreCase;

        private static final boolean DEFAULT_IGNORE_CASE = false;

        public Order(@Nullable Direction direction, String property) {
            this(direction, property, DEFAULT_IGNORE_CASE);
        }

        public static Order by(String property) {
            return new Order(DEFAULT_DIRECTION, property);
        }

        public static Order asc(String property) {
            return new Order(Direction.ASC, property);
        }

        public static Order desc(String property) {
            return new Order(Direction.DESC, property);
        }

        private Order(@Nullable Direction direction, String property, boolean ignoreCase) {

            if (!StringUtils.hasText(property)) {
                throw new IllegalArgumentException("Property must not null or empty!");
            }

            this.direction = direction == null ? DEFAULT_DIRECTION : direction;
            this.property = property;
            this.ignoreCase = ignoreCase;
        }

        public Direction getDirection() {
            return direction;
        }

        public String getProperty() {
            return property;
        }

        public boolean isAscending() {
            return this.direction.isAscending();
        }

        public boolean isDescending() {
            return this.direction.isDescending();
        }
        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public Order with(Direction direction) {
            return new Order(direction, this.property, this.ignoreCase);
        }

        public Order withProperty(String property) {
            return new Order(this.direction, property, this.ignoreCase);
        }

        public Order ignoreCase() {
            return new Order(direction, property, true);
        }

        @Override
        public int hashCode() {

            int result = 17;

            result = 31 * result + direction.hashCode();
            result = 31 * result + property.hashCode();
            result = 31 * result + (ignoreCase ? 1 : 0);

            return result;
        }

        @Override
        public boolean equals(@Nullable Object obj) {

            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Order)) {
                return false;
            }

            Order that = (Order) obj;

            return this.direction.equals(that.direction) && this.property.equals(that.property)
                    && this.ignoreCase == that.ignoreCase;
        }

        @Override
        public String toString() {

            String result = String.format("%s: %s", property, direction);

            if (ignoreCase) {
                result += ", ignoring case";
            }

            return result;
        }
    }

    public enum Direction {
        ASC, DESC;

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }

        public static Direction fromString(String value) {

            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
            }
        }

        public static Optional<Direction> fromOptionalString(String value) {

            try {
                return Optional.of(fromString(value));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }
    }
}
