package cm.amcloud.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import cm.amcloud.platform.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Override
    @NonNull
    List<Reservation> findAll();

    // The save method is inherited from JpaRepository and does not need to be redeclared.
}
