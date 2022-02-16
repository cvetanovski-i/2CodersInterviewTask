package com.ivancvetanovski.a2codersinterviewtask.ui.base.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Base for other activities.
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

  protected lateinit var binding: VB

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = inflateLayout(layoutInflater)
    setContentView(binding.root)
  }

  abstract fun inflateLayout(layoutInflater: LayoutInflater): VB
}