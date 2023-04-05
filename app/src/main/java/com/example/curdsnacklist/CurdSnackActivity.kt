package com.example.curdsnacklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import com.example.curdsnacklist.adapter.ItemAdapter
import com.example.curdsnacklist.databinding.ActivityMainBinding
import com.example.curdsnacklist.fragments.CurdSnackInfoFragmentDirections
import com.example.curdsnacklist.models.CurdSnack
import java.util.*

class CurdSnackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val curdSnackList = arrayListOf<CurdSnack>(
        CurdSnack(
            "Ожина",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238706",
            "https://voloshkovepole.com.ua/wp-content/uploads/2021/01/surokozhina.jpg"
                 ),

        CurdSnack(
            "Полуниця",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004233541",
            "https://voloshkovepole.com.ua/wp-content/uploads/2019/06/surokpolunica.jpg"
                 ),

        CurdSnack(
            "Кокос",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238553",
            "https://voloshkovepole.com.ua/wp-content/uploads/2021/01/surokkokos.jpg"
                 ),

        CurdSnack(
            "Вишня",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004233527",
            "https://voloshkovepole.com.ua/wp-content/uploads/2019/06/surokvushnya.jpg"
                 ),

        CurdSnack(
            "Манго",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238713",
            "https://voloshkovepole.com.ua/wp-content/uploads/2021/01/surokmango.jpg"
                 ),

        CurdSnack(
            "Кава",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238676",
            "https://voloshkovepole.com.ua/wp-content/uploads/2021/01/surokkava.jpg"
                 ),

        CurdSnack(
            "Какао",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238676",
            "https://voloshkovepole.com.ua/wp-content/uploads/2019/06/surokkakao.jpg"
                 ),

        CurdSnack(
            "Ванілін",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238676",
            "https://voloshkovepole.com.ua/wp-content/uploads/2019/06/surokvanyl.jpg"
                 ),

        CurdSnack(
            "Персик",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238676",
            "https://voloshkovepole.com.ua/wp-content/uploads/2019/06/sirok-persik.jpg"
                 ),

        CurdSnack(
            "Горіх",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238676",
            "https://voloshkovepole.com.ua/wp-content/uploads/2019/06/sirok-orex.jpg"
                 ),

        CurdSnack(
            "Карамель",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238676",
            "https://voloshkovepole.com.ua/wp-content/uploads/2019/06/sirok-karamel.jpg"
                 ),

        CurdSnack(
            "Згущене молоко",
            "26%",
            "36",
            "30",
            "поліпропілен",
            "+2/+6/-18/-22",
            "14/30/180",
            "4820004238676",
            "https://voloshkovepole.com.ua/wp-content/uploads/2019/06/sirok-sgushenka.jpg"
                 )
                                                      )

    private val adapter = ItemAdapter(curdSnackList, ::launchFragment)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCurdSnackList.adapter = adapter
        binding.rvCurdSnackList.layoutManager = GridLayoutManager(this, 3)

        adapter.selectedCurdSnack(stack_item_positions.peek())

        binding.buttonLeft.setOnClickListener {
            previousCurdSnack()
        }

        binding.buttonRight.setOnClickListener {
            nextCurdSnack()
        }
    }

    // hardware back button
    override fun onBackPressed() {
        if (stack_item_positions.size > 1) {
            stack_item_positions.pop()
            adapter.selectedCurdSnack(stack_item_positions.peek())
            super.onBackPressed()
        }
        else {
            super.onBackPressed()
        }
    }

    // function for adapter to launch fragment when item was clicked in recycler view
    private fun launchFragment(curdSnack: CurdSnack) {

        val direction =
            CurdSnackInfoFragmentDirections.actionCurdSnackInfoFragmentSelf(curdSnack)

        findNavController(R.id.fragmentContainer).navigate(direction, navOptions {
            anim {
                enter = R.anim.from_right
                exit = R.anim.to_left
                popEnter = R.anim.from_left
                popExit = R.anim.to_right
            }
        })
    }

    // next curd snack item in fragment container
    private fun nextCurdSnack() {
        if (stack_item_positions.peek() == 11) return

        stack_item_positions.add(stack_item_positions.peek() + 1)

        val destination =
            CurdSnackInfoFragmentDirections.actionCurdSnackInfoFragmentSelf(curdSnackList[stack_item_positions.peek()])

        findNavController(R.id.fragmentContainer).navigate(destination,
            navOptions {
                anim {
                    enter = R.anim.from_right
                    exit = R.anim.to_left
                    popEnter = R.anim.from_left
                    popExit = R.anim.to_right
                }
            })

        adapter.selectedCurdSnack(stack_item_positions.peek())
    }

    // previous curd snack item in fragment container
    private fun previousCurdSnack() {
        if (stack_item_positions.size > 1) {
            stack_item_positions.pop()

            adapter.selectedCurdSnack(stack_item_positions.peek())

            supportFragmentManager.popBackStack()
        }
    }

    companion object {
        private val stack_item_positions = Stack<Int>().also { it.add(0) }

        fun newItemSelected(item_position: Int) {
            stack_item_positions.add(item_position)
        }
    }
}