package com.yashswi.dilpay.models;

import java.util.ArrayList;

public class MyBookingsModel {
    String BusSnos, username, TripId, BoardingId, DroppingId, NoofSeats, Fares, Servicetax, ServiceCharge, commission, Seatcodes, Titles,
            Address, PostalCode, IdCardType, IdCardNo, IdCardIssuedBy, MobileNo, EmailId, SourceId, DestinationId,
            JourneyDate, TripType, SourceName, DestinationName, Provider, Operator, DisplayName, BusTypeName, BusType, BoardingPointDetails,
            DroppingPointDetails, DepartureTime, ArrivalTime, CancellationPolicy, PartialCancellationAllowed, ConvenienceFee, UserType, IsIdProofRequried,
            Blocking_ReferenceNo, Booking_ReferenceNo, Message, orderId, orderAmount, orderCurrency, orderNote, cftoken, TicketPNR, TicketRefferance, whatsappMessage,
            smsMessage, emailMessage, PaymentStatus, Ticketstatus, PaymentID, paymentMode, Invoice_No, Payment_Date, CancelTicketStatus, Cancel_Message, DayofCancellation,
            TimeofCancellation, DayOfBooking, TimeOfBooking;
    ArrayList<String> Names = new ArrayList<>();
    ArrayList<String> Ages = new ArrayList<>();
    ArrayList<String> Genders = new ArrayList<>();
    ArrayList<String> SeatNos = new ArrayList<>();

    public ArrayList<String> getNames() {
        return Names;
    }

    public void setNames(ArrayList<String> names) {
        this.Names = names;
    }

    public ArrayList<String> getAges() {
        return Ages;
    }

    public void setAges(ArrayList<String> ages) {
        this.Ages = ages;
    }

    public ArrayList<String> getGenders() {
        return Genders;
    }

    public void setGenders(ArrayList<String> genders) {
        this.Genders = genders;
    }

    public ArrayList<String> getSeatNos() {
        return SeatNos;
    }

    public void setSeatNos(ArrayList<String> seatNos) {
        SeatNos = seatNos;
    }

    public String getBusSnos() {
        return BusSnos;
    }

    public void setBusSnos(String busSnos) {
        BusSnos = busSnos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTripId() {
        return TripId;
    }

    public void setTripId(String tripId) {
        TripId = tripId;
    }

    public String getBoardingId() {
        return BoardingId;
    }

    public void setBoardingId(String boardingId) {
        BoardingId = boardingId;
    }

    public String getDroppingId() {
        return DroppingId;
    }

    public void setDroppingId(String droppingId) {
        DroppingId = droppingId;
    }

    public String getNoofSeats() {
        return NoofSeats;
    }

    public void setNoofSeats(String noofSeats) {
        NoofSeats = noofSeats;
    }

    public String getFares() {
        return Fares;
    }

    public void setFares(String fares) {
        Fares = fares;
    }

    public String getServicetax() {
        return Servicetax;
    }

    public void setServicetax(String servicetax) {
        Servicetax = servicetax;
    }

    public String getServiceCharge() {
        return ServiceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        ServiceCharge = serviceCharge;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }


    public String getSeatcodes() {
        return Seatcodes;
    }

    public void setSeatcodes(String seatcodes) {
        Seatcodes = seatcodes;
    }

    public String getTitles() {
        return Titles;
    }

    public void setTitles(String titles) {
        Titles = titles;
    }


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getIdCardType() {
        return IdCardType;
    }

    public void setIdCardType(String idCardType) {
        IdCardType = idCardType;
    }

    public String getIdCardNo() {
        return IdCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        IdCardNo = idCardNo;
    }

    public String getIdCardIssuedBy() {
        return IdCardIssuedBy;
    }

    public void setIdCardIssuedBy(String idCardIssuedBy) {
        IdCardIssuedBy = idCardIssuedBy;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getSourceId() {
        return SourceId;
    }

    public void setSourceId(String sourceId) {
        SourceId = sourceId;
    }

    public String getDestinationId() {
        return DestinationId;
    }

    public void setDestinationId(String destinationId) {
        DestinationId = destinationId;
    }

    public String getJourneyDate() {
        return JourneyDate;
    }

    public void setJourneyDate(String journeyDate) {
        JourneyDate = journeyDate;
    }

    public String getTripType() {
        return TripType;
    }

    public void setTripType(String tripType) {
        TripType = tripType;
    }

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void setDestinationName(String destinationName) {
        DestinationName = destinationName;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getBusTypeName() {
        return BusTypeName;
    }

    public void setBusTypeName(String busTypeName) {
        BusTypeName = busTypeName;
    }

    public String getBusType() {
        return BusType;
    }

    public void setBusType(String busType) {
        BusType = busType;
    }

    public String getBoardingPointDetails() {
        return BoardingPointDetails;
    }

    public void setBoardingPointDetails(String boardingPointDetails) {
        BoardingPointDetails = boardingPointDetails;
    }

    public String getDroppingPointDetails() {
        return DroppingPointDetails;
    }

    public void setDroppingPointDetails(String droppingPointDetails) {
        DroppingPointDetails = droppingPointDetails;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getCancellationPolicy() {
        return CancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        CancellationPolicy = cancellationPolicy;
    }

    public String getPartialCancellationAllowed() {
        return PartialCancellationAllowed;
    }

    public void setPartialCancellationAllowed(String partialCancellationAllowed) {
        PartialCancellationAllowed = partialCancellationAllowed;
    }

    public String getConvenienceFee() {
        return ConvenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        ConvenienceFee = convenienceFee;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getIsIdProofRequried() {
        return IsIdProofRequried;
    }

    public void setIsIdProofRequried(String isIdProofRequried) {
        IsIdProofRequried = isIdProofRequried;
    }

    public String getBlocking_ReferenceNo() {
        return Blocking_ReferenceNo;
    }

    public void setBlocking_ReferenceNo(String blocking_ReferenceNo) {
        Blocking_ReferenceNo = blocking_ReferenceNo;
    }

    public String getBooking_ReferenceNo() {
        return Booking_ReferenceNo;
    }

    public void setBooking_ReferenceNo(String booking_ReferenceNo) {
        Booking_ReferenceNo = booking_ReferenceNo;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String getCftoken() {
        return cftoken;
    }

    public void setCftoken(String cftoken) {
        this.cftoken = cftoken;
    }

    public String getTicketPNR() {
        return TicketPNR;
    }

    public void setTicketPNR(String ticketPNR) {
        TicketPNR = ticketPNR;
    }

    public String getTicketRefferance() {
        return TicketRefferance;
    }

    public void setTicketRefferance(String ticketRefferance) {
        TicketRefferance = ticketRefferance;
    }

    public String getWhatsappMessage() {
        return whatsappMessage;
    }

    public void setWhatsappMessage(String whatsappMessage) {
        this.whatsappMessage = whatsappMessage;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getTicketstatus() {
        return Ticketstatus;
    }

    public void setTicketstatus(String ticketstatus) {
        Ticketstatus = ticketstatus;
    }

    public String getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(String paymentID) {
        PaymentID = paymentID;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getInvoice_No() {
        return Invoice_No;
    }

    public void setInvoice_No(String invoice_No) {
        Invoice_No = invoice_No;
    }

    public String getPayment_Date() {
        return Payment_Date;
    }

    public void setPayment_Date(String payment_Date) {
        Payment_Date = payment_Date;
    }

    public String getCancelTicketStatus() {
        return CancelTicketStatus;
    }

    public void setCancelTicketStatus(String cancelTicketStatus) {
        CancelTicketStatus = cancelTicketStatus;
    }

    public String getCancel_Message() {
        return Cancel_Message;
    }

    public void setCancel_Message(String cancel_Message) {
        Cancel_Message = cancel_Message;
    }

    public String getDayofCancellation() {
        return DayofCancellation;
    }

    public void setDayofCancellation(String dayofCancellation) {
        DayofCancellation = dayofCancellation;
    }

    public String getTimeofCancellation() {
        return TimeofCancellation;
    }

    public void setTimeofCancellation(String timeofCancellation) {
        TimeofCancellation = timeofCancellation;
    }

    public String getDayOfBooking() {
        return DayOfBooking;
    }

    public void setDayOfBooking(String dayOfBooking) {
        DayOfBooking = dayOfBooking;
    }

    public String getTimeOfBooking() {
        return TimeOfBooking;
    }

    public void setTimeOfBooking(String timeOfBooking) {
        TimeOfBooking = timeOfBooking;
    }
}