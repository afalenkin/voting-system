package edu.alenkin.topjavagraduation.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Represents the vote given for a specific restaurant.
 * Users can vote on which restaurant they want to have lunch at.
 * Only one vote counted per user.
 */

@ApiModel(value = "The vote given for a specific restaurant")
@Entity
@Table(name = "vote")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ApiModelProperty(example = "2021-09-03T19:20:32.298678700")
    @Column(name = "datetime")
    @NotNull
    private LocalDateTime voteDateTime;

    public Vote(Integer id, User user, Restaurant restaurant, LocalDateTime voteDateTime) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDateTime = voteDateTime;
    }

    public Vote(User user, Restaurant restaurant, LocalDateTime voteDateTime) {
        this(null, user, restaurant, voteDateTime);
    }
}