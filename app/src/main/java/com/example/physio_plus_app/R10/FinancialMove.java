package com.example.physio_plus_app.R10;

import android.widget.LinearLayout;

import com.example.physio_plus_app.Utils.AppObserver;
import com.example.physio_plus_app.Utils.Entities.Patient;
import com.example.physio_plus_app.Utils.Entities.PhysioCenter;
import com.example.physio_plus_app.Utils.Entities.Session;

public class FinancialMove {
    private PhysioCenter physioCenter;
    private Session session;
    public FinancialMove(PhysioCenter physioCenter, Session session) {
        this.physioCenter = physioCenter;
        this.session = session;
    }

    public void show(LinearLayout linearLayout) {
        LinearLayout fm_linearLayout = new LinearLayout(linearLayout.getContext());
        fm_linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.physioCenter.show(fm_linearLayout);
        this.session.show(fm_linearLayout);
        linearLayout.addView(fm_linearLayout);
    }

    public String getHashKey() {
        return this.physioCenter.getHashKey();
    }

    public double getCost() {
        return this.session.getCost();
    }

    public void showCenterDetails(LinearLayout linearLayout) {
        this.physioCenter.show(linearLayout);
    }

    public void showServiceDetails(LinearLayout linearLayout) {
        LinearLayout services_linearLayout = new LinearLayout(linearLayout.getContext());
        services_linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.session.show(services_linearLayout);
        linearLayout.addView(services_linearLayout);
    }
}
