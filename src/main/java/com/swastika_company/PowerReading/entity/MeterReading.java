package com.swastika_company.PowerReading.entity;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="reading")

@NamedQuery(name="MeterReading.fetchAllMeterAndReading",
query="""
		SELECT new com.swastika_company.PowerReading.dto.MeterAndReading(r.date,r.time,r.kwh,r.pf,m.machineId,m.meterName) from MeterReading r inner join r.meter m 
		"""
)

public class MeterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double kwh;
    private float pf;

    @Column(name="reading_date")
    private LocalDate date;

    @Override
	public String toString() {
		return "MeterReading [id=" + id + ", kwh=" + kwh + ", pf=" + pf + ", date=" + date + ", time=" + time
				;
	}
	@Column(name="reading_time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "meter_id") // This matches the @Column name in Meter class
    private Meter meter;
	public Meter getMeter() {
		return meter;
	}
	public void setMeter(Meter meter) {
		this.meter = meter;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getKwh() {
		return kwh;
	}
	public void setKwh(double kwh) {
		this.kwh = kwh;
	}
	public float getPf() {
		return pf;
	}
	public void setPf(float pf) {
		this.pf = pf;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	

}