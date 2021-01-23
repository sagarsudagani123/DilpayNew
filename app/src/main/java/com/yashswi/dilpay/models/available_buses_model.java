package com.yashswi.dilpay.models;


import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class available_buses_model implements Serializable {


    String DisplayName,AvailableSeats,IsRtc, ArrivalTime,BusType,CancellationPolicy,idProofRequired,DepartureTime,DestinationId,Duration,Fares,ServiceTax,OperatorServiceCharge,
    ConvenienceFee,ConvenienceFeeType,AffiliateId,NetFares,Commission,CommissionType,OperatorId,Id,Provider,PartialCancellationAllowed,Seattype,SourceId,Travels,Mticket,Journeydate;
    ArrayList<String> addressBoard=new ArrayList<>();
    ArrayList<String> contactPersonsBoard=new ArrayList<>();
    ArrayList<String> contactNumbersBoard=new ArrayList<>();
    ArrayList<String> pointIdboard=new ArrayList<>();
    ArrayList<String> landmarkBoard=new ArrayList<>();
    ArrayList<String> locationBoard=new ArrayList<>();
    ArrayList<String> nameBoard=new ArrayList<>();
    ArrayList<String> timeBoard=new ArrayList<>();
    ArrayList<String> addressDrop=new ArrayList<>();
    ArrayList<String> contactPersonsDrop=new ArrayList<>();
    ArrayList<String> contactNumbersDrop=new ArrayList<>();
    ArrayList<String> pointIdDrop=new ArrayList<>();
    ArrayList<String> landmarkDrop=new ArrayList<>();
    ArrayList<String> locationDrop=new ArrayList<>();
    ArrayList<String> nameDrop=new ArrayList<>();
    ArrayList<String> timeDrop=new ArrayList<>();


    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        this.DisplayName = displayName;
    }

    public String getAvailableSeats() {
        return AvailableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        this.AvailableSeats = availableSeats;
    }

    public String getIsRtc() {
        return IsRtc;
    }

    public void setIsRtc(String isRtc) {
        this.IsRtc = isRtc;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.ArrivalTime = arrivalTime;
    }


    public String getBusType() {
        return BusType;
    }

    public void setBusType(String busType) {
        this.BusType = busType;
    }

    public String getCancellationPolicy() {
        return CancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.CancellationPolicy = cancellationPolicy;
    }

    public String getIdProofRequired() {
        return idProofRequired;
    }

    public void setIdProofRequired(String idProofRequired) {
        this.idProofRequired = idProofRequired;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.DepartureTime = departureTime;
    }

    public String getDestinationId() {
        return DestinationId;
    }

    public void setDestinationId(String destinationId) {
        this.DestinationId = destinationId;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        this.Duration = duration;
    }

    public String getFares() {
        return Fares;
    }

    public void setFares(String fares) {
        this.Fares = fares;
    }

    public String getServiceTax() {
        return ServiceTax;
    }

    public void setServiceTax(String serviceTax) {
        this.ServiceTax = serviceTax;
    }

    public String getOperatorServiceCharge() {
        return OperatorServiceCharge;
    }

    public void setOperatorServiceCharge(String operatorServiceCharge) {
        this.OperatorServiceCharge = operatorServiceCharge;
    }

    public String getConvenienceFee() {
        return ConvenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        this.ConvenienceFee = convenienceFee;
    }

    public String getConvenienceFeeType() {
        return ConvenienceFeeType;
    }

    public void setConvenienceFeeType(String convenienceFeeType) {
        this.ConvenienceFeeType = convenienceFeeType;
    }

    public String getAffiliateId() {
        return AffiliateId;
    }

    public void setAffiliateId(String affiliateId) {
        this.AffiliateId = affiliateId;
    }

    public String getNetFares() {
        return NetFares;
    }

    public void setNetFares(String netFares) {
        this.NetFares = netFares;
    }

    public String getCommission() {
        return Commission;
    }

    public void setCommission(String commission) {
        this.Commission = commission;
    }

    public String getCommissionType() {
        return CommissionType;
    }

    public void setCommissionType(String commissionType) {
        this.CommissionType = commissionType;
    }

    public String getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(String operatorId) {
        this.OperatorId = operatorId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        this.Provider = provider;
    }

    public String getPartialCancellationAllowed() {
        return PartialCancellationAllowed;
    }

    public void setPartialCancellationAllowed(String partialCancellationAllowed) {
        this.PartialCancellationAllowed = partialCancellationAllowed;
    }

    public String getSeattype() {
        return Seattype;
    }

    public void setSeattype(String seattype) {
        this.Seattype = seattype;
    }

    public String getSourceId() {
        return SourceId;
    }

    public void setSourceId(String sourceId) {
        this.SourceId = sourceId;
    }

    public String getTravels() {
        return Travels;
    }

    public void setTravels(String travels) {
        this.Travels = travels;
    }

    public String getMticket() {
        return Mticket;
    }

    public void setMticket(String mticket) {
        this.Mticket = mticket;
    }

    public String getJourneydate() {
        return Journeydate;
    }

    public void setJourneydate(String journeydate) {
        this.Journeydate = journeydate;
    }

    public ArrayList<String> getAddressBoard() {
        return addressBoard;
    }

    public void setAddressBoard(String addressBoard) {
        this.addressBoard.add(addressBoard);
    }

    public ArrayList<String> getContactPersonsBoard() {
        return contactPersonsBoard;
    }

    public void setContactPersonsBoard(String contactPersonsBoard) {
        this.contactPersonsBoard.add(contactPersonsBoard);
    }

    public ArrayList<String> getContactNumbersBoard() {
        return contactNumbersBoard;
    }

    public void setContactNumbersBoard(String contactNumbersBoard) {
        this.contactNumbersBoard.add(contactNumbersBoard);
    }

    public ArrayList<String> getPointIdboard() {
        return pointIdboard;
    }

    public void setPointIdboard(String pointIdboard) {
        this.pointIdboard.add(pointIdboard);
    }

    public ArrayList<String> getLandmarkBoard() {
        return landmarkBoard;
    }

    public void setLandmarkBoard(String landmarkBoard) {
        this.landmarkBoard.add(landmarkBoard);
    }

    public ArrayList<String> getLocationBoard() {
        return locationBoard;
    }

    public void setLocationBoard(String locationBoard) {
        this.locationBoard.add(locationBoard);
    }

    public ArrayList<String> getNameBoard() {
        return nameBoard;
    }

    public void setNameBoard(String nameBoard) {
        this.nameBoard.add(nameBoard);
    }

    public ArrayList<String> getTimeBoard() {
        return timeBoard;
    }

    public void setTimeBoard(String timeBoard) {
        this.timeBoard.add(timeBoard);
    }

    public ArrayList<String> getAddressDrop() {
        return addressDrop;
    }

    public void setAddressDrop(String addressDrop) {
        this.addressDrop.add(addressDrop);
    }

    public ArrayList<String> getContactPersonsDrop() {
        return contactPersonsDrop;
    }

    public void setContactPersonsDrop(String contactPersonsDrop) {
        this.contactPersonsDrop.add(contactPersonsDrop);
    }

    public ArrayList<String> getContactNumbersDrop() {
        return contactNumbersDrop;
    }

    public void setContactNumbersDrop(String contactNumbersDrop) {
        this.contactNumbersDrop.add(contactNumbersDrop);
    }

    public ArrayList<String> getPointIdDrop() {
        return pointIdDrop;
    }

    public void setPointIdDrop(String pointIdDrop) {
        this.pointIdDrop.add(pointIdDrop);
    }

    public ArrayList<String> getLandmarkDrop() {
        return landmarkDrop;
    }

    public void setLandmarkDrop(String landmarkDrop) {
        this.landmarkDrop.add(landmarkDrop);
    }

    public ArrayList<String> getLocationDrop() {
        return locationDrop;
    }

    public void setLocationDrop(String locationDrop) {
        this.locationDrop.add(locationDrop);
    }

    public ArrayList<String> getNameDrop() {
        return nameDrop;
    }

    public void setNameDrop(String nameDrop) {
        this.nameDrop.add(nameDrop);
    }

    public ArrayList<String> getTimeDrop() {
        return timeDrop;
    }

    public void setTimeDrop(String timeDrop) {
        this.timeDrop.add(timeDrop);
    }
}
