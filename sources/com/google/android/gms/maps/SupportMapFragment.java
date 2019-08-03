package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.p000v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.internal.C0048ab;
import com.google.android.gms.internal.C0059ag;
import com.google.android.gms.internal.C0091at;
import com.google.android.gms.internal.C0102d;
import com.google.android.gms.internal.C0113g;
import com.google.android.gms.internal.C0128n;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class SupportMapFragment extends Fragment {

    /* renamed from: aJ */
    private final C0183b f207aJ = new C0183b(this);

    /* renamed from: aK */
    private GoogleMap f208aK;

    /* renamed from: com.google.android.gms.maps.SupportMapFragment$a */
    static class C0182a implements LifecycleDelegate {

        /* renamed from: ag */
        private final Fragment f209ag;

        /* renamed from: ah */
        private final IMapFragmentDelegate f210ah;

        public C0182a(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.f210ah = (IMapFragmentDelegate) C0091at.m107c(iMapFragmentDelegate);
            this.f209ag = (Fragment) C0091at.m107c(fragment);
        }

        /* renamed from: g */
        public IMapFragmentDelegate mo1562g() {
            return this.f210ah;
        }

        public void onCreate(Bundle bundle) {
            if (bundle == null) {
                try {
                    bundle = new Bundle();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            Bundle arguments = this.f209ag.getArguments();
            if (arguments != null && arguments.containsKey("MapOptions")) {
                C0113g.m153a(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
            }
            this.f210ah.onCreate(bundle);
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) C0048ab.m23a(this.f210ah.onCreateView(C0048ab.m22a(layoutInflater), C0048ab.m22a(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroy() {
            try {
                this.f210ah.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            try {
                this.f210ah.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.f210ah.onInflate(C0048ab.m22a(activity), (GoogleMapOptions) bundle.getParcelable("MapOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onLowMemory() {
            try {
                this.f210ah.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.f210ah.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.f210ah.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.f210ah.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    /* renamed from: com.google.android.gms.maps.SupportMapFragment$b */
    static class C0183b extends C0102d<C0182a> {

        /* renamed from: ag */
        private final Fragment f211ag;

        /* renamed from: f */
        protected C0059ag<C0182a> f212f;

        /* renamed from: g */
        private Activity f213g;

        C0183b(Fragment fragment) {
            this.f211ag = fragment;
        }

        /* access modifiers changed from: private */
        public void setActivity(Activity activity) {
            this.f213g = activity;
            mo1563a();
        }

        /* renamed from: a */
        public void mo1563a() {
            if (this.f213g != null && this.f212f != null && mo1310aa() == null) {
                try {
                    MapsInitializer.initialize(this.f213g);
                    this.f212f.mo1209b(new C0182a(this.f211ag, C0128n.m185a((Context) this.f213g).mo1178c(C0048ab.m22a(this.f213g))));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo1309a(C0059ag<C0182a> agVar) {
            this.f212f = agVar;
            mo1563a();
        }
    }

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(GoogleMapOptions googleMapOptions) {
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        supportMapFragment.setArguments(bundle);
        return supportMapFragment;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public IMapFragmentDelegate mo1560g() {
        this.f207aJ.mo1563a();
        if (this.f207aJ.mo1310aa() == null) {
            return null;
        }
        return ((C0182a) this.f207aJ.mo1310aa()).mo1562g();
    }

    public GoogleMap getMap() {
        IMapFragmentDelegate g = mo1560g();
        if (g == null) {
            return null;
        }
        try {
            IGoogleMapDelegate map = g.getMap();
            if (map == null) {
                return null;
            }
            if (this.f208aK == null || this.f208aK.mo1449ab().asBinder() != map.asBinder()) {
                this.f208aK = new GoogleMap(map);
            }
            return this.f208aK;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f207aJ.setActivity(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f207aJ.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f207aJ.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.f207aJ.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.f207aJ.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.f207aJ.setActivity(activity);
        GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", createFromAttributes);
        this.f207aJ.onInflate(activity, bundle2, bundle);
    }

    public void onLowMemory() {
        this.f207aJ.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.f207aJ.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f207aJ.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.f207aJ.onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
