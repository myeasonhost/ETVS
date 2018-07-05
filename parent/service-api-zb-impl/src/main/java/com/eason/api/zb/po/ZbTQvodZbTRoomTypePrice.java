package com.eason.api.zb.po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "qvod_zb_t_room_type_price")
public class ZbTQvodZbTRoomTypePrice implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String type;
    private Integer time;
    private Integer timeInterval;
    private String price;
    private String optUser;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Basic
    @Column(name = "time_interval", nullable = true)
    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Basic
    @Column(name = "price", nullable = true, length = 50)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Basic
    @Column(name = "opt_user", nullable = true, length = 50)
    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZbTQvodZbTRoomTypePrice that = (ZbTQvodZbTRoomTypePrice) o;
        return Objects.equals(id, that.id)  &&
                Objects.equals(type, that.type) &&
                Objects.equals(time, that.time) &&
                Objects.equals(timeInterval, that.timeInterval) &&
                Objects.equals(price, that.price) &&
                Objects.equals(optUser, that.optUser) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, time, timeInterval, price, optUser, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "ZbTQvodZbTRoomTypePrice{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", time=" + time +
                ", timeInterval=" + timeInterval +
                ", price='" + price + '\'' +
                ", optUser='" + optUser + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
