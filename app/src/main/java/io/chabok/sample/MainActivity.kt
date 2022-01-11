package io.chabok.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.chabok.Callback
import io.chabok.Chabok
import io.chabok.sample.databinding.ActivityMainBinding
import io.chabok.user.Gender
import io.chabok.user.Profile
import java.util.*
import kotlin.collections.HashMap

class MainActivity: AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener {
            if(binding.loginUerIdEditTxt.text.toString() != "") {
                Chabok.user().login(binding.loginUerIdEditTxt.text.toString(), object : Callback {
                    override fun onResponse(success: Boolean, message: String?) {
                        if(!success) {
                            Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            } else {
                Toast.makeText(this,"مقدار UserId را وارد کنید.",Toast.LENGTH_SHORT).show()
            }
        }

        binding.logoutBtn.setOnClickListener {
            Chabok.user().logout(object : Callback {
                override fun onResponse(success: Boolean, message: String?) {
                    if(!success) {
                        Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.sendAttributeBtn.setOnClickListener {
            Chabok.user().setAttribute(binding.attributeKeyEditTxt.text.toString(),
                binding.attributeValueEditTxt.text.toString(), object : Callback {
                    override fun onResponse(success: Boolean, message: String?) {
                        if(!success) {
                            Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@MainActivity, "success",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }

        binding.eventBtn.setOnClickListener {
            val name = binding.eventKeyEditTxt.text.toString()
            val body = HashMap<String,Any>()

            Chabok.analytics().sendEvent(name,body, object : Callback {
                override fun onResponse(success: Boolean, message: String?) {
                    if(!success) {
                        Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.eventSample1Btn.setOnClickListener {
            val id = "nx3183783793712897bcc"
            val body = HashMap<String,Any>()
            body["keywords"] = arrayOf("banana", "cheese", "apple")

            Chabok.analytics().sendEvent(id,body, object : Callback {
                override fun onResponse(success: Boolean, message: String?) {
                    if(!success) {
                        Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.eventSample2Btn.setOnClickListener {
            val id = "nx423789x478n4723897n7x0823"
            val body = HashMap<String,Any>()
            body["orderId"] = UUID.randomUUID()
            body["order"] = arrayOf("banana")

            Chabok.analytics().sendEvent(id,body, object : Callback {
                override fun onResponse(success: Boolean, message: String?) {
                    if(!success) {
                        Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.eventSample3Btn.setOnClickListener {
            val id = "61d1658aa26a49e10e955d12"
            val body = HashMap<String,Any>()
            body["revenue"] = 180000
            body["currency"] = "RIAL"
            body["orderId"] = UUID.randomUUID()
            body["order"] = arrayOf("banana")

            Chabok.analytics().sendEvent(id,body, object : Callback {
                override fun onResponse(success: Boolean, message: String?) {
                    if(!success) {
                        Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.eventSample4Btn.setOnClickListener {
            val id = "obx837n83912x789n7129x"
            val body = HashMap<String,Any>()
            body["revenue"] = 250000
            body["currency"] = "RIAL"
            body["orderId"] = UUID.randomUUID()
            body["order"] = arrayOf("banana", "cheese", "apple")

            Chabok.analytics().sendEvent(id,body, object : Callback {
                override fun onResponse(success: Boolean, message: String?) {
                    if(!success) {
                        Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.updateProfileBtn.setOnClickListener {
            val profile = Profile.Builder()
                .email(binding.profileEmailEditTxt.text.toString())
                .mobile(binding.profileMobileEditTxt.text.toString())
                .name(binding.profileNameEditTxt.text.toString())
                .family(binding.profileFamilyEditTxt.text.toString())
                .birthDate(binding.profileBirthDateEditTxt.text.toString())
                .timeZone(binding.profileTimeZoneEditTxt.text.toString())
                .gender(Gender.MALE)
                .build()
            Chabok.user().setProfile(profile, object : Callback {
                override fun onResponse(success: Boolean, message: String?) {
                    if(!success) {
                        Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.profileSampleBtn.setOnClickListener {
            val profile = Profile.Builder()
                .email("ho@gmail.com")
                .mobile("+989100360500")
                .name("Hossein")
                .family("Shooshtari")
                .birthDate("1993-05-19")
                .timeZone("+3:30")
                .gender(Gender.MALE)
                .build()
            Chabok.user().setProfile(profile, object : Callback {
                override fun onResponse(success: Boolean, message: String?) {
                    if(!success) {
                        Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.updateLocationBtn.setOnClickListener {
            val latitude = binding.locationLatitudeEditTxt.text.toString()
            val longitude = binding.locationLongitudeEditTxt.text.toString()
            if(latitude != "" && longitude != "") {
                Chabok.user().setLocation(latitude.toDouble(),longitude.toDouble(), object : Callback {
                    override fun onResponse(success: Boolean, message: String?) {
                        if(!success) {
                            Toast.makeText(this@MainActivity, message,Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

    }

    fun showInitState(state: Boolean) {
        if (state) {
            binding.initializeTxt.text = ("Initialize 🟢")
        } else {
            binding.initializeTxt.text = ("Initialize 🔴")
        }
    }
}