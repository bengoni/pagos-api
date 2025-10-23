package com.bienesraices.pagos.api.repository;



import com.bienesraices.pagos.api.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> { }
