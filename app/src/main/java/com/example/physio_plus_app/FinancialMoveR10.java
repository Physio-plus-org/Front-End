package com.example.physio_plus_app;

import android.widget.LinearLayout;

public class FinancialMoveR10 {
    private PhysioCenterR10 physioCenter;
    private SessionR10 session;
    public FinancialMoveR10(PhysioCenterR10 physioCenter, SessionR10 session) {
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
}
