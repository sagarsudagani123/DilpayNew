package com.yashswi.dilpay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashswi.dilpay.Api_interface.cashFree;
import com.yashswi.dilpay.R;
import com.yashswi.dilpay.bank.WithdrawAmount;
import com.yashswi.dilpay.bank.BankAccounts;
import com.yashswi.dilpay.models.bankDetails;
import com.yashswi.dilpay.models.userDetails;

import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BankAccountsAdapter extends RecyclerView.Adapter<BankAccountsAdapter.MyViewHolder> {
    ArrayList<bankDetails> bankDetails;
    ArrayList<String> IFSC;
    String title;
    Context context;
    static String tokenFinal;
    RelativeLayout progress;
    JSONObject test=null;

    public BankAccountsAdapter(String title, ArrayList<bankDetails> bankDetails, RelativeLayout progress, Context context) {
        this.bankDetails = bankDetails;
        this.title = title;
        this.context = context;
        this.progress=progress;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_bank_accounts, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.acntNumber.setText(bankDetails.get(position).getAcntNumber());
        holder.IFSC.setText(bankDetails.get(position).getIfscCode());
        if (title.equalsIgnoreCase("Select Bank")) {
            holder.delete.setVisibility(View.GONE);
//            holder.edit.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WithdrawAmount.class);
                    intent.putExtra("beneficiaryID", bankDetails.get(position).getBeneficiaryID());
                    intent.putExtra("emial", bankDetails.get(position).getEmial());
                    intent.putExtra("accountNumber", bankDetails.get(position).getAcntNumber());
                    intent.putExtra("IFSC", bankDetails.get(position).getIfscCode());
                    intent.putExtra("address", bankDetails.get(position).getAddress());
                    context.startActivity(intent);
                }
            });
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);


                getToken(bankDetails.get(position).getBeneficiaryID());
//                deleted=((BankAccounts)context).removeBeneficiary(tokenFinal,bankDetails.get(position).getBeneficiaryID());

                test = new JSONObject();
                try {
                    test.put("beneficiaryID", bankDetails.get(position).getBeneficiaryID());
                    test.put("username", new userDetails(context).getNumber());
                    test.put("Name", new userDetails(context).getName());
                    test.put("emial", bankDetails.get(position).getEmial());
                    test.put("phone", new userDetails(context).getNumber());
                    test.put("accountNumber", bankDetails.get(position).getAcntNumber());
                    test.put("IFSC", bankDetails.get(position).getIfscCode());
                    test.put("address", bankDetails.get(position).getAddress());
                    test.put("Method", "Delete");
                } catch (Exception e) {
                }


//                deleteAccount(test.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return bankDetails.size();
    }




    void getToken(String benId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(cashFree.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        cashFree api = retrofit.create(cashFree.class);
        Call<String> call = api.getToken("CF4207C0VN927A55OA211PLK80", "4eac8173d6023b23482d984c298b2cd69c7672cc");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject data = new JSONObject(response.body());
                    if (data.getString("status").equalsIgnoreCase("SUCCESS")) {
                        JSONObject tokenObj = data.getJSONObject("data");
                        tokenFinal = tokenObj.getString("token");
                        ((BankAccounts)context).removeBeneficiary(tokenFinal,benId,test.toString());
//                        deleteAccount(deleted);
//                        Toast.makeText(context,"deleted="+deleted,Toast.LENGTH_SHORT).show();
                    } else {
                        tokenFinal = "";
                        Toast.makeText(context,data.toString(),Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "";
                if (t instanceof UnknownHostException) {
                    message = "No internet connection";
                } else {
                    message = "Something went wrong! try again";
                }
                Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    void deleteAccount(boolean deleted){
//        Log.e("bankDetails==delete",test.toString());
//        if(deleted){
//            ((BankAccounts) context).deleteAccount(test.toString());
//        }
//        else{
//            Toast.makeText(context,"Account can't be deleted",Toast.LENGTH_SHORT).show();
//        }
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView acntNumber, IFSC;
        ImageView delete;
//        AppCompatButton edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            acntNumber = itemView.findViewById(R.id.acntNum);
            IFSC = itemView.findViewById(R.id.ifscNum);
            delete = itemView.findViewById(R.id.delete);
//            edit = itemView.findViewById(R.id.edit);
        }
    }
}
