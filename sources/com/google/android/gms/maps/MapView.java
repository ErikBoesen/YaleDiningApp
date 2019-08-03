package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.internal.C0048ab;
import com.google.android.gms.internal.C0059ag;
import com.google.android.gms.internal.C0091at;
import com.google.android.gms.internal.C0102d;
import com.google.android.gms.internal.C0128n;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class MapView extends FrameLayout {

    /* renamed from: aK */
    private GoogleMap f198aK;

    /* renamed from: bq */
    private final C0180a f199bq;

    /* renamed from: com.google.android.gms.maps.MapView$a */
    static class C0180a extends C0102d<C0181b> {

        /* renamed from: cC */
        private final ViewGroup f200cC;

        /* renamed from: cD */
        private final GoogleMapOptions f201cD;

        /* renamed from: f */
        protected C0059ag<C0181b> f202f;
        private final Context mContext;

        C0180a(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.f200cC = viewGroup;
            this.mContext = context;
            this.f201cD = googleMapOptions;
        }

        /* renamed from: a */
        public void mo1555a() {
            if (this.f202f != null && mo1310aa() == null) {
                try {
                    this.f202f.mo1209b(new C0181b(this.f200cC, C0128n.m185a(this.mContext).mo1176a(C0048ab.m22a(this.mContext), this.f201cD)));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo1309a(C0059ag<C0181b> agVar) {
            this.f202f = agVar;
            mo1555a();
        }
    }

    /* renamed from: com.google.android.gms.maps.MapView$b */
    static class C0181b implements LifecycleDelegate {

        /* renamed from: cR */
        private final ViewGroup f203cR;

        /* renamed from: cS */
        private final IMapViewDelegate f204cS;

        /* renamed from: cT */
        private View f205cT;

        public C0181b(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.f204cS = (IMapViewDelegate) C0091at.m107c(iMapViewDelegate);
            this.f203cR = (ViewGroup) C0091at.m107c(viewGroup);
        }

        /* renamed from: ah */
        public IMapViewDelegate mo1556ah() {
            return this.f204cS;
        }

        public void onCreate(Bundle bundle) {
            try {
                this.f204cS.onCreate(bundle);
                this.f205cT = (View) C0048ab.m23a(this.f204cS.getView());
                this.f203cR.removeAllViews();
                this.f203cR.addView(this.f205cT);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        public void onDestroy() {
            try {
                this.f204cS.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }

        public void onLowMemory() {
            try {
                this.f204cS.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.f204cS.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.f204cS.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.f204cS.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public MapView(Context context) {
        super(context);
        this.f199bq = new C0180a(this, context, null);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f199bq = new C0180a(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f199bq = new C0180a(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
    }

    public MapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context);
        this.f199bq = new C0180a(this, context, googleMapOptions);
    }

    public GoogleMap getMap() {
        if (this.f198aK != null) {
            return this.f198aK;
        }
        this.f199bq.mo1555a();
        if (this.f199bq.mo1310aa() == null) {
            return null;
        }
        try {
            this.f198aK = new GoogleMap(((C0181b) this.f199bq.mo1310aa()).mo1556ah().getMap());
            return this.f198aK;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void onCreate(Bundle bundle) {
        this.f199bq.onCreate(bundle);
        if (this.f199bq.mo1310aa() == null) {
            this.f199bq.mo1308a((FrameLayout) this);
        }
    }

    public final void onDestroy() {
        this.f199bq.onDestroy();
    }

    public final void onLowMemory() {
        this.f199bq.onLowMemory();
    }

    public final void onPause() {
        this.f199bq.onPause();
    }

    public final void onResume() {
        this.f199bq.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.f199bq.onSaveInstanceState(bundle);
    }
}
