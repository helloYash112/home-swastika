package com.swastika_company.PowerReading.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="meter")
public class Meter {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="meter_id")
    private Long meterId;

   
	
	@Column(name="machine_id")
    private String machineId;

    @Column(name="meter_name")
    private String meterName;

    @Column(name="meter_number")
    private String meterNumber;
    
    @OneToMany(mappedBy="meter", cascade = CascadeType.ALL)
    private List<MeterReading> meterReadings=new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<MeterReading> getMeterReading() {
		return meterReadings;
	}
	public void setMeterReading(List<MeterReading> meterReading) {
		this.meterReadings = meterReading;
	}
	public Long getMeterId() {
		return meterId;
	}
	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}
	public String getMacId() {
		return machineId;
	}
	public void setMacId(String macId) {
		this.machineId = macId;
	}
	public String getMeterNo() {
		return meterNumber;
	}
	public void setMeterNo(String meterNo) {
		this.meterNumber = meterNo;
	}
	public String getMeterName() {
		return meterName;
	}
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	 @Override
	public String toString() {
		return "Meter [meterId=" + meterId + ", machineId=" + machineId + ", meterName=" + meterName + ", meterNumber="
				+ meterNumber ;
	}

}