/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.toyvpn

import android.app.Activity
import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Button

class ToyVpnClient : Activity(), View.OnClickListener {
    private var mServerAddress: TextView? = null
    private var mServerPort: TextView? = null
    private var mSharedSecret: TextView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form)

        findViewById(R.id.connect).setOnClickListener(this)

        val prefix = packageName
        val intent = Intent(this, ToyVpnService::class.java)
        startService(intent)
    }

    override fun onClick(v: View) {
        val intent = VpnService.prepare(this)
        if (intent != null) {
            startActivityForResult(intent, 0)
        } else {
            onActivityResult(0, Activity.RESULT_OK, null)
        }
    }

    override fun onActivityResult(request: Int, result: Int, data: Intent?) {
        if (result == Activity.RESULT_OK) {
            val prefix = packageName
            val intent = Intent(this, ToyVpnService::class.java)
            startService(intent)
        }
    }
}