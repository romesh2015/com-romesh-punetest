package com.romesh.punetest.profile.view.ui
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.facebook.drawee.backends.pipeline.Fresco
import com.romesh.punetest.R
import com.romesh.punetest.appdb.AppDatabase
import com.romesh.punetest.network.ErrorResponse
import com.romesh.punetest.profile.model.ApiResponse
import com.romesh.punetest.profile.presenter.ProfilePresenter
import com.romesh.punetest.profile.presenter.ProfileView
import com.romesh.punetest.profile.view.adapter.ProfilesAdapter
import com.romesh.punetest.profile.view.adapter.ProfilesOfflineAdapter
import com.romesh.punetest.utility.Constant.SWIPE_ACTION
import com.romesh.punetest.utility.Tracer
import com.romesh.punetest.utility.Utility
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.android.synthetic.main.activity_profile.*
/*
* This is main activity to handle all operations about user profile
 */
class ProfileActivity : AppCompatActivity(), CardStackListener,
    ProfileView {
    val TAG="ProfileActivity"
    private val adapter =ProfilesAdapter()
    private val adapterOffline= ProfilesOfflineAdapter()
    private var apiResponse:ApiResponse?=null
    private lateinit var layoutManager: CardStackLayoutManager
    private val profilePresenter=ProfilePresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_profile)


        layoutManager = CardStackLayoutManager(this, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        stack_view.layoutManager = layoutManager
        stack_view.adapter = adapter
        stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }
    override fun onStart() {
        super.onStart()
        // Api call to show user detail
        Tracer.debug(TAG,"onStart called...")
        hitApi()
    }
    private fun hitApi(){
        if(Utility.isConnected()){
            profilePresenter.getUserProfile()
        }else{
            Toast.makeText(this,getString(R.string.internet_not_found),Toast.LENGTH_SHORT).show()
            stack_view.adapter = adapterOffline
            var data= AppDatabase(this)
            var recordFromDB=data?.viewProfile()
            if(recordFromDB.size>0){
                adapterOffline.setProfiles(recordFromDB)
            }else{
                Toast.makeText(this,getString(R.string.record_not_found_db),Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCardDisappeared(view: View?, position: Int) {
        if(Utility.isConnected()){
            hitApi()
        }
    }
    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }
    override fun onCardSwiped(direction: Direction?) {
        val swipeAction= direction?.name
        if(swipeAction==SWIPE_ACTION){
            var data= AppDatabase(this)
            apiResponse?.let { data.addProfileFavourite(it) }
            Tracer.info(TAG,apiResponse.toString())
        }

    }
    override fun onCardCanceled() {
    }
    override fun onCardAppeared(view: View?, position: Int) {

    }
    override fun onCardRewound() {
    }

    override fun onSuccess(apiResponse: ApiResponse?) {
        this.apiResponse=apiResponse;
        adapter.setProfiles(apiResponse!!.results)
    }

    override fun onFailure(errorResponse: ErrorResponse?) {
        Toast.makeText(this,errorResponse?.error,Toast.LENGTH_SHORT).show()
    }
    override fun onShowLoadingView() {

    }
}
