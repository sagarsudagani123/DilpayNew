package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yashswi.dilpay.bus.Booking_details;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.bus.Bus_seating;
import com.yashswi.dilpay.models.MyBookingsModel;
import com.yashswi.dilpay.models.available_buses_model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Bookings_list_adapter extends RecyclerView.Adapter<Bookings_list_adapter.MyViewHolder> {
    ArrayList<String> from;
    ArrayList<String> to;
    ArrayList<String> status;
    ArrayList<String> from_time;
    ArrayList<String> to_time;
    ArrayList<String> date;
    ArrayList<String> travels;
    ArrayList<MyBookingsModel> retroModelArray_list;
    Context context;
    String data;
    String BusSnos, username, TripId, BoardingId, DroppingId, NoofSeats, Fares, Servicetax, ServiceCharge, commission, Seatcodes, Titles,
            Address, PostalCode, IdCardType, IdCardNo, IdCardIssuedBy, MobileNo, EmailId, SourceId, DestinationId,
            JourneyDate, TripType, SourceName, DestinationName, Provider, Operator, DisplayName, BusTypeName, BusType, BoardingPointDetails,
            DroppingPointDetails, DepartureTime, ArrivalTime, CancellationPolicy, PartialCancellationAllowed, ConvenienceFee, UserType, IsIdProofRequried,
            Blocking_ReferenceNo, Booking_ReferenceNo, Message, orderId, orderAmount, orderCurrency, orderNote, cftoken, TicketPNR, TicketRefferance, whatsappMessage,
            smsMessage, emailMessage, PaymentStatus, Ticketstatus, PaymentID, paymentMode, Invoice_No, Payment_Date, CancelTicketStatus, Cancel_Message, DayofCancellation,
            TimeofCancellation, DayOfBooking, TimeOfBooking, Names, Ages, Genders, SeatNos;

    JSONObject dataObj=null;
    //    ArrayList<String> Names=new ArrayList<>();
//    ArrayList<String> Ages=new ArrayList<>();
//    ArrayList<String> Genders=new ArrayList<>();
//    ArrayList<String> SeatNos=new ArrayList<>();
    public Bookings_list_adapter(ArrayList<String> from, ArrayList<String> to, ArrayList<String> status, ArrayList<String> from_time, ArrayList<String> to_time, ArrayList<String> date, ArrayList<String> travels, ArrayList<MyBookingsModel> retroModelArray_list, String data, Context context) {
        this.from = from;
        this.to = to;
        this.status = status;
        this.from_time = from_time;
        this.to_time = to_time;
        this.date = date;
        this.travels = travels;
        this.retroModelArray_list = retroModelArray_list;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View a = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_my_bookings_list, parent, false);
        MyViewHolder holder = new MyViewHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.pickup.setText(from.get(position));
        holder.drop.setText(to.get(position));
        holder.status1.setText(status.get(position));
        holder.pickup_time.setText(from_time.get(position));
        holder.drop_time.setText(to_time.get(position));
        holder.journey_date.setText(date.get(position));
        holder.travels.setText(travels.get(position));


        try {
            JSONObject obj = new JSONObject(data);
            JSONArray jsonArray = obj.getJSONArray("Data");
            dataObj = jsonArray.getJSONObject(position);
            BusSnos = dataObj.getString("BusSnos");
            username = dataObj.getString("username");
            TripId = dataObj.getString("TripId");
            BoardingId = dataObj.getString("BoardingId");
            DroppingId = dataObj.getString("DroppingId");
            NoofSeats = dataObj.getString("NoofSeats");
            Fares = dataObj.getString("Fares");
            Servicetax = dataObj.getString("Servicetax");
            ServiceCharge = dataObj.getString("ServiceCharge");
            commission = dataObj.getString("commission");
            SeatNos = dataObj.getString("SeatNos");
            Seatcodes = dataObj.getString("Seatcodes");
            Titles = dataObj.getString("Titles");
            Names = dataObj.getString("Names");
            Ages = dataObj.getString("Ages");
            Genders = dataObj.getString("Genders");
            Address = dataObj.getString("Address");
            PostalCode = dataObj.getString("PostalCode");
            IdCardType = dataObj.getString("IdCardType");
            IdCardNo = dataObj.getString("IdCardNo");
            IdCardIssuedBy = dataObj.getString("IdCardIssuedBy");
            MobileNo = dataObj.getString("MobileNo");
            EmailId = dataObj.getString("EmailId");
            SourceId = dataObj.getString("SourceId");
            DestinationId = dataObj.getString("DestinationId");
            JourneyDate = dataObj.getString("JourneyDate");
            TripType = dataObj.getString("TripType");
            SourceName = dataObj.getString("SourceName");
            DestinationName = dataObj.getString("DestinationName");
            Provider = dataObj.getString("Provider");
            Operator = dataObj.getString("Operator");
            DisplayName = dataObj.getString("DisplayName");
            BusTypeName = dataObj.getString("BusTypeName");
            BusType = dataObj.getString("BusType");
            BoardingPointDetails = dataObj.getString("BoardingPointDetails");
            DroppingPointDetails = dataObj.getString("DroppingPointDetails");
            DepartureTime = dataObj.getString("DepartureTime");
            ArrivalTime = dataObj.getString("ArrivalTime");
            CancellationPolicy = dataObj.getString("CancellationPolicy");
            PartialCancellationAllowed = dataObj.getString("PartialCancellationAllowed");
            ConvenienceFee = dataObj.getString("ConvenienceFee");
            UserType = dataObj.getString("UserType");
            IsIdProofRequried = dataObj.getString("IsIdProofRequried");
            Blocking_ReferenceNo = dataObj.getString("Blocking_ReferenceNo");
            Booking_ReferenceNo = dataObj.getString("Booking_ReferenceNo");
            Message = dataObj.getString("Message");
            orderId = dataObj.getString("orderId");
            orderAmount = dataObj.getString("orderAmount");
            orderCurrency = dataObj.getString("orderCurrency");
            orderNote = dataObj.getString("orderNote");
            cftoken = dataObj.getString("cftoken");
            TicketPNR = dataObj.getString("TicketPNR");
            TicketRefferance = dataObj.getString("TicketRefferance");
            whatsappMessage = dataObj.getString("whatsappMessage");
            smsMessage = dataObj.getString("smsMessage");
            emailMessage = dataObj.getString("emailMessage");
            PaymentStatus = dataObj.getString("PaymentStatus");
            Ticketstatus = dataObj.getString("Ticketstatus");
            PaymentID = dataObj.getString("PaymentID");
            paymentMode = dataObj.getString("paymentMode");
            Invoice_No = dataObj.getString("Invoice_No");
            Payment_Date = dataObj.getString("Payment_Date");
            CancelTicketStatus = dataObj.getString("CancelTicketStatus");
            Cancel_Message = dataObj.getString("Cancel_Message");
            DayofCancellation = dataObj.getString("DayofCancellation");
            TimeofCancellation = dataObj.getString("TimeofCancellation");
            DayOfBooking = dataObj.getString("DayOfBooking");
            TimeOfBooking = dataObj.getString("TimeOfBooking");


//            final String username = retroModelArray_list.get(position).getUsername();
//            final String TripId = retroModelArray_list.get(position).getTripId();
//            final String BoardingId = retroModelArray_list.get(position).getBoardingId();
//            final String DroppingId = retroModelArray_list.get(position).getDroppingId();
//            final String NoofSeats = retroModelArray_list.get(position).getNoofSeats();
//            final String Fares = retroModelArray_list.get(position).getFares();
//            final String Servicetax = retroModelArray_list.get(position).getServicetax();
//            final String ServiceCharge = retroModelArray_list.get(position).getServiceCharge();
//            final String commission = retroModelArray_list.get(position).getCommission();
//            final ArrayList<String> SeatNos = retroModelArray_list.get(position).getSeatNos();
//            final String Seatcodes = retroModelArray_list.get(position).getSeatcodes();
//            final String Titles = retroModelArray_list.get(position).getTitles();
//            final ArrayList<String> Names = retroModelArray_list.get(position).getNames();
//            final ArrayList<String> Ages = retroModelArray_list.get(position).getAges();
//            final ArrayList<String> Genders = retroModelArray_list.get(position).getGenders();
//            final String Address = retroModelArray_list.get(position).getAddress();
//            final String PostalCode = retroModelArray_list.get(position).getPostalCode();
//            final String IdCardType = retroModelArray_list.get(position).getIdCardType();
//            final String IdCardNo = retroModelArray_list.get(position).getIdCardNo();
//            final String IdCardIssuedBy = retroModelArray_list.get(position).getIdCardIssuedBy();
//            final String MobileNo = retroModelArray_list.get(position).getMobileNo();
//            final String EmailId = retroModelArray_list.get(position).getEmailId();
//            final String SourceId = retroModelArray_list.get(position).getSourceId();
//            final String DestinationId = retroModelArray_list.get(position).getDestinationId();
//            final String JourneyDate = retroModelArray_list.get(position).getJourneyDate();
//            final String TripType = retroModelArray_list.get(position).getTripType();
//            final String SourceName = retroModelArray_list.get(position).getSourceName();
//            final String DestinationName = retroModelArray_list.get(position).getDestinationName();
//            final String Provider = retroModelArray_list.get(position).getProvider();
//            final String Operator = retroModelArray_list.get(position).getOperator();
//            final String DisplayName = retroModelArray_list.get(position).getDisplayName();
//            final String BusTypeName = retroModelArray_list.get(position).getBusTypeName();
//            final String BusType = retroModelArray_list.get(position).getBusType();
//            final String BoardingPointDetails = retroModelArray_list.get(position).getBoardingPointDetails();
//            final String DroppingPointDetails = retroModelArray_list.get(position).getDroppingPointDetails();
//            final String DepartureTime = retroModelArray_list.get(position).getDepartureTime();
//            final String ArrivalTime = retroModelArray_list.get(position).getArrivalTime();
//            final String CancellationPolicy = retroModelArray_list.get(position).getCancellationPolicy();
//            final String PartialCancellationAllowed = retroModelArray_list.get(position).getPartialCancellationAllowed();
//            final String ConvenienceFee = retroModelArray_list.get(position).getConvenienceFee();
//            final String UserType = retroModelArray_list.get(position).getUserType();
//            final String IsIdProofRequried = retroModelArray_list.get(position).getIsIdProofRequried();
//            final String Blocking_ReferenceNo = retroModelArray_list.get(position).getBlocking_ReferenceNo();
//            final String Booking_ReferenceNo = retroModelArray_list.get(position).getBooking_ReferenceNo();
//            final String Message = retroModelArray_list.get(position).getMessage();
//            final String orderId = retroModelArray_list.get(position).getOrderId();
//            final String orderAmount = retroModelArray_list.get(position).getOrderAmount();
//            final String orderCurrency = retroModelArray_list.get(position).getOrderCurrency();
//            final String orderNote = retroModelArray_list.get(position).getOrderNote();
//            final String cftoken = retroModelArray_list.get(position).getCftoken();
//            final String TicketPNR = retroModelArray_list.get(position).getTicketPNR();
//            final String TicketRefferance = retroModelArray_list.get(position).getTicketRefferance();
//            final String whatsappMessage = retroModelArray_list.get(position).getWhatsappMessage();
//            final String smsMessage = retroModelArray_list.get(position).getSmsMessage();
//            final String emailMessage = retroModelArray_list.get(position).getEmailMessage();
//            final String PaymentStatus = retroModelArray_list.get(position).getPaymentStatus();
//            final String Ticketstatus = retroModelArray_list.get(position).getTicketstatus();
//            final String PaymentID = retroModelArray_list.get(position).getPaymentID();
//            final String paymentMode = retroModelArray_list.get(position).getPaymentMode();
//            final String Invoice_No = retroModelArray_list.get(position).getInvoice_No();
//            final String Payment_Date = retroModelArray_list.get(position).getPayment_Date();
//            final String CancelTicketStatus = retroModelArray_list.get(position).getCancelTicketStatus();
//            final String Cancel_Message = retroModelArray_list.get(position).getCancel_Message();
//            final String DayofCancellation = retroModelArray_list.get(position).getDayofCancellation();
//            final String TimeofCancellation = retroModelArray_list.get(position).getTimeofCancellation();
//            final String DayOfBooking = retroModelArray_list.get(position).getDayOfBooking();
//            final String TimeOfBooking = retroModelArray_list.get(position).getTimeOfBooking();
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Booking_details.class);
                intent.putExtra("JSONDATA", dataObj.toString());
                intent.putExtra("BusSnos", BusSnos);
                intent.putExtra("username", username);
                intent.putExtra("TripId", TripId);
                intent.putExtra("BoardingId", BoardingId);
                intent.putExtra("DroppingId", DroppingId);
                intent.putExtra("NoofSeats", NoofSeats);
                intent.putExtra("Fares", Fares);
                intent.putExtra("Servicetax", Servicetax);
                intent.putExtra("ServiceCharge", ServiceCharge);
                intent.putExtra("commission", commission);
                intent.putExtra("SeatNos", SeatNos);
                intent.putExtra("Seatcodes", Seatcodes);
                intent.putExtra("Titles", Titles);
                intent.putExtra("Names", Names);
                intent.putExtra("Ages", Ages);
                intent.putExtra("Genders", Genders);
                intent.putExtra("Address", Address);
                intent.putExtra("PostalCode", PostalCode);
                intent.putExtra("IdCardType", IdCardType);
                intent.putExtra("IdCardNo", IdCardNo);
                intent.putExtra("IdCardIssuedBy", IdCardIssuedBy);
                intent.putExtra("MobileNo", MobileNo);
                intent.putExtra("EmailId", EmailId);
                intent.putExtra("SourceId", SourceId);
                intent.putExtra("DestinationId", DestinationId);
                intent.putExtra("JourneyDate", JourneyDate);
                intent.putExtra("TripType", TripType);
                intent.putExtra("SourceName", SourceName);
                intent.putExtra("DestinationName", DestinationName);
                intent.putExtra("Provider", Provider);
                intent.putExtra("Operator", Operator);
                intent.putExtra("DisplayName", DisplayName);
                intent.putExtra("BusTypeName", BusTypeName);
                intent.putExtra("BusType", BusType);
                intent.putExtra("BoardingPointDetails", BoardingPointDetails);
                intent.putExtra("DroppingPointDetails", DroppingPointDetails);
                intent.putExtra("DepartureTime", DepartureTime);
                intent.putExtra("ArrivalTime", ArrivalTime);
                intent.putExtra("CancellationPolicy", CancellationPolicy);
                intent.putExtra("PartialCancellationAllowed", PartialCancellationAllowed);
                intent.putExtra("ConvenienceFee", ConvenienceFee);
                intent.putExtra("UserType", UserType);
                intent.putExtra("IsIdProofRequried", IsIdProofRequried);
                intent.putExtra("Blocking_ReferenceNo", Blocking_ReferenceNo);
                intent.putExtra("Booking_ReferenceNo", Booking_ReferenceNo);
                intent.putExtra("Message", Message);
                intent.putExtra("orderId", orderId);
                intent.putExtra("orderAmount", orderAmount);
                intent.putExtra("orderCurrency", orderCurrency);
                intent.putExtra("orderNote", orderNote);
                intent.putExtra("cftoken", cftoken);
                intent.putExtra("TicketPNR", TicketPNR);
                intent.putExtra("TicketRefferance", TicketRefferance);
                intent.putExtra("whatsappMessage", whatsappMessage);
                intent.putExtra("smsMessage", smsMessage);
                intent.putExtra("emailMessage", emailMessage);
                intent.putExtra("PaymentStatus", PaymentStatus);
                intent.putExtra("Ticketstatus", Ticketstatus);
                intent.putExtra("PaymentID", PaymentID);
                intent.putExtra("paymentMode", paymentMode);
                intent.putExtra("Invoice_No", Invoice_No);
                intent.putExtra("Payment_Date", Payment_Date);
                intent.putExtra("CancelTicketStatus", CancelTicketStatus);
                intent.putExtra("Cancel_Message", Cancel_Message);
                intent.putExtra("DayofCancellation", DayofCancellation);
                intent.putExtra("TimeofCancellation", TimeofCancellation);
                intent.putExtra("DayOfBooking", DayOfBooking);
                intent.putExtra("TimeOfBooking", TimeOfBooking);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return from.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pickup, drop, status1, pickup_time, drop_time, journey_date, travels;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pickup = itemView.findViewById(R.id.pickup);
            drop = itemView.findViewById(R.id.drop);
            status1 = itemView.findViewById(R.id.status);
            pickup_time = itemView.findViewById(R.id.pickup_time);
            drop_time = itemView.findViewById(R.id.drop_time);
            journey_date = itemView.findViewById(R.id.journey_date);
            cardView = itemView.findViewById(R.id.bookings_card);
            travels = itemView.findViewById(R.id.travels);
        }
    }
}