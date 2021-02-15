package com.yashswi.dilpay.models;


import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class available_buses_model implements Serializable {

    String DisplayName,AvailableSeats,IsRtc,IdProofRequired,isOpTicketTemplateRequired,isOpLogoRequired,ArrivalTime,Amenities,
            BusType,SeatLayoutType,CancellationPolicy,MaxSeatsPerTicket,DepartureTime,DestinationId,Duration,
            Fares,ServiceTax,OperatorServiceCharge,ConvenienceFee,ConvenienceFeeType,AffiliateId,NetFares,PartnerFee,
            OperatorId,BpDpSeatLayout,Id,Provider,PartialCancellationAllowed,SeatType,SourceId,Travels,Mticket,InventoryType,Journeydate;


    //bording details
    ArrayList<String> addressBoard=new ArrayList<>();
    ArrayList<String> contactPersonsBoard=new ArrayList<>();
    ArrayList<String> contactNumbersBoard=new ArrayList<>();
    ArrayList<String> pointIdboard=new ArrayList<>();
    ArrayList<String> landmarkBoard=new ArrayList<>();
    ArrayList<String> locationBoard=new ArrayList<>();
    ArrayList<String> nameBoard=new ArrayList<>();
    ArrayList<String> timeBoard=new ArrayList<>();

    //droping details
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
        DisplayName = displayName;
    }

    public String getAvailableSeats() {
        return AvailableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        AvailableSeats = availableSeats;
    }

    public String getIsRtc() {
        return IsRtc;
    }

    public void setIsRtc(String isRtc) {
        IsRtc = isRtc;
    }



    public String getIsOpTicketTemplateRequired() {
        return isOpTicketTemplateRequired;
    }

    public void setIsOpTicketTemplateRequired(String isOpTicketTemplateRequired) {
        this.isOpTicketTemplateRequired = isOpTicketTemplateRequired;
    }

    public String getIsOpLogoRequired() {
        return isOpLogoRequired;
    }

    public void setIsOpLogoRequired(String isOpLogoRequired) {
        this.isOpLogoRequired = isOpLogoRequired;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getAmenities() {
        return Amenities;
    }

    public void setAmenities(String amenities) {
        Amenities = amenities;
    }

    public String getBusType() {
        return BusType;
    }

    public void setBusType(String busType) {
        BusType = busType;
    }

    public String getSeatLayoutType() {
        return SeatLayoutType;
    }

    public void setSeatLayoutType(String seatLayoutType) {
        SeatLayoutType = seatLayoutType;
    }

    public String getCancellationPolicy() {
        return CancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        CancellationPolicy = cancellationPolicy;
    }

    public String getIdProofRequired() {
        return IdProofRequired;
    }

    public void setIdProofRequired(String idProofRequired) {
        IdProofRequired = idProofRequired;
    }

    public String getMaxSeatsPerTicket() {
        return MaxSeatsPerTicket;
    }

    public void setMaxSeatsPerTicket(String maxSeatsPerTicket) {
        MaxSeatsPerTicket = maxSeatsPerTicket;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public String getDestinationId() {
        return DestinationId;
    }

    public void setDestinationId(String destinationId) {
        DestinationId = destinationId;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getFares() {
        return Fares;
    }

    public void setFares(String fares) {
        Fares = fares;
    }

    public String getServiceTax() {
        return ServiceTax;
    }

    public void setServiceTax(String serviceTax) {
        ServiceTax = serviceTax;
    }

    public String getOperatorServiceCharge() {
        return OperatorServiceCharge;
    }

    public void setOperatorServiceCharge(String operatorServiceCharge) {
        OperatorServiceCharge = operatorServiceCharge;
    }

    public String getConvenienceFee() {
        return ConvenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        ConvenienceFee = convenienceFee;
    }

    public String getConvenienceFeeType() {
        return ConvenienceFeeType;
    }

    public void setConvenienceFeeType(String convenienceFeeType) {
        ConvenienceFeeType = convenienceFeeType;
    }

    public String getAffiliateId() {
        return AffiliateId;
    }

    public void setAffiliateId(String affiliateId) {
        AffiliateId = affiliateId;
    }

    public String getNetFares() {
        return NetFares;
    }

    public void setNetFares(String netFares) {
        NetFares = netFares;
    }

    public String getPartnerFee() {
        return PartnerFee;
    }

    public void setPartnerFee(String partnerFee) {
        PartnerFee = partnerFee;
    }

    public String getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(String operatorId) {
        OperatorId = operatorId;
    }

    public String getBpDpSeatLayout() {
        return BpDpSeatLayout;
    }

    public void setBpDpSeatLayout(String bpDpSeatLayout) {
        BpDpSeatLayout = bpDpSeatLayout;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }

    public String getPartialCancellationAllowed() {
        return PartialCancellationAllowed;
    }

    public void setPartialCancellationAllowed(String partialCancellationAllowed) {
        PartialCancellationAllowed = partialCancellationAllowed;
    }

    public String getSeatType() {
        return SeatType;
    }

    public void setSeatType(String seatType) {
        SeatType = seatType;
    }

    public String getSourceId() {
        return SourceId;
    }

    public void setSourceId(String sourceId) {
        SourceId = sourceId;
    }

    public String getTravels() {
        return Travels;
    }

    public void setTravels(String travels) {
        Travels = travels;
    }

    public String getMticket() {
        return Mticket;
    }

    public void setMticket(String mticket) {
        Mticket = mticket;
    }

    public String getInventoryType() {
        return InventoryType;
    }

    public void setInventoryType(String inventoryType) {
        InventoryType = inventoryType;
    }

    public String getJourneydate() {
        return Journeydate;
    }

    public void setJourneydate(String journeydate) {
        Journeydate = journeydate;
    }

    public void setAddressBoard(ArrayList<String> addressBoard) {
        this.addressBoard = addressBoard;
    }

    public void setContactPersonsBoard(ArrayList<String> contactPersonsBoard) {
        this.contactPersonsBoard = contactPersonsBoard;
    }

    public void setContactNumbersBoard(ArrayList<String> contactNumbersBoard) {
        this.contactNumbersBoard = contactNumbersBoard;
    }

    public void setPointIdboard(ArrayList<String> pointIdboard) {
        this.pointIdboard = pointIdboard;
    }

    public void setLandmarkBoard(ArrayList<String> landmarkBoard) {
        this.landmarkBoard = landmarkBoard;
    }

    public void setLocationBoard(ArrayList<String> locationBoard) {
        this.locationBoard = locationBoard;
    }

    public void setNameBoard(ArrayList<String> nameBoard) {
        this.nameBoard = nameBoard;
    }

    public void setTimeBoard(ArrayList<String> timeBoard) {
        this.timeBoard = timeBoard;
    }

    public void setAddressDrop(ArrayList<String> addressDrop) {
        this.addressDrop = addressDrop;
    }

    public void setContactPersonsDrop(ArrayList<String> contactPersonsDrop) {
        this.contactPersonsDrop = contactPersonsDrop;
    }

    public void setContactNumbersDrop(ArrayList<String> contactNumbersDrop) {
        this.contactNumbersDrop = contactNumbersDrop;
    }

    public void setPointIdDrop(ArrayList<String> pointIdDrop) {
        this.pointIdDrop = pointIdDrop;
    }

    public void setLandmarkDrop(ArrayList<String> landmarkDrop) {
        this.landmarkDrop = landmarkDrop;
    }

    public void setLocationDrop(ArrayList<String> locationDrop) {
        this.locationDrop = locationDrop;
    }

    public void setNameDrop(ArrayList<String> nameDrop) {
        this.nameDrop = nameDrop;
    }

    public void setTimeDrop(ArrayList<String> timeDrop) {
        this.timeDrop = timeDrop;
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
