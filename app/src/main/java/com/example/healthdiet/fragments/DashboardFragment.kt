package com.example.healthdiet.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.healthdiet.classes.Diet
import com.example.healthdiet.classes.Product
import com.example.healthdiet.databinding.FragmentDashboardBinding
import com.example.healthdiet.viewmodels.DashboardViewModel
import org.w3c.dom.Text

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    ////////////products and diets/////////////

    val beef = Product("Говядина", "%5B%5D=110")
    val mutton = Product("Баранина", "%5B%5D=111")
    val chicken = Product("Курица", "%5B%5D=36")
    val fish = Product("Рыба", "%5B%5D=20")
    val curd = Product("Творог", "%5B%5D=191")
    val oatmeal = Product("Овсяная крупа", "%5B%5D=527")
    val buckwheat = Product("Гречневая крупа", "%5B%5D=242")
    val millet = Product("Пшено", "%5B%5D=213")
    val vegetables = Product("Свежие овощи", "%5B%5D=23")
    val dough = Product("Сдобное тесто", "%5B%5D=343")
    val sausage = Product("Колбасные изделия", "%5B%5D=122")
    val pork = Product("Свинина", "%5B%5D=124")
    val duck = Product("Утка", "%5B%5D=181")
    val goose = Product("Гусь", "%5B%5D=154")
    val rice = Product("Рис", "%5B%5D=129")
    val semolina = Product("Манная крупа", "%5B%5D=182")
    val pasta = Product("Макаронные изделия", "%5B%5D=1566")
    val sugar = Product("Сахар", "%5B%5D=332")
    val animalFat = Product("Животный жир", "%5B%5D=10349")


    var diet9 = Diet(
        "Стол №9", mutableListOf(beef, mutton, chicken, fish, curd, oatmeal, buckwheat, millet, vegetables),
        mutableListOf(dough, sausage, pork, duck, goose, rice, semolina, pasta, sugar)
    )

    ///////////////////////////////////////////


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val args = DashboardFragmentArgs.fromBundle(requireArguments())
        val item = args.number

        val chosenDietTv: TextView = binding.chosenDietTv
        if(item != 0) {
            chosenDietTv.text = "Выбрана диета: Стол №${item}"
        }

        val changeDietBtn: Button = binding.changeDietBtn
        changeDietBtn.setOnClickListener{
            val action = DashboardFragmentDirections.actionNavigationDashboardToChooseDiet()
            findNavController().navigate(action)
        }

        val showGreenProductsBtn: Button = binding.greenListBtn
        showGreenProductsBtn.setOnClickListener{
            val action = DashboardFragmentDirections.actionNavigationDashboardToDescWithBtn(diet9, 1)
            findNavController().navigate(action)
        }
        val showRedProductsBtn: Button = binding.redListBtn
        showRedProductsBtn.setOnClickListener{
            val action = DashboardFragmentDirections.actionNavigationDashboardToDescWithBtn(diet9, 2)
            findNavController().navigate(action)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}