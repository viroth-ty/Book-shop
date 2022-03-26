package org.viroth.bookstore.app.view.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.viroth.bookstore.app.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val viewPager = ViewPagerAdapter(fm = this)
        binding.viewPager.adapter = viewPager
        binding.dotsIndicator.setViewPager2(binding.viewPager)
    }
}