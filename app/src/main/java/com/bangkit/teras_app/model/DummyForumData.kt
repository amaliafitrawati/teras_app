package com.bangkit.teras_app.model

import android.annotation.SuppressLint
import com.bangkit.teras_app.R
import java.text.SimpleDateFormat

object DummyForumData {
    @SuppressLint("SimpleDateFormat")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    val forum = listOf(
        Forum(
            1,
            "Pertumbuhan Tanaman",
            "Alhamdulillah panen beras hari ini dapat terpenuhi dengan baik." +
                    "Cukup untuk persedian setahun kedepan Alhamdulillah panen beras hari ini dapat terpe"+
                    " nuhi dengan baik. Cukup untuk pesds",
            R.drawable.img_dummy,
            dateFormat.parse("2023-12-07T06:44:41.000Z")

        ),
        Forum(
            2,
            "Tanya Dong",
            "Mau nanya ya",
            R.drawable.img_dummy,
            dateFormat.parse("2023-12-07T06:44:41.000Z")

        ),
        Forum(
            3,
            "pertumbuhan tanaman",
            "Test Data",
            R.drawable.img_dummy,
            dateFormat.parse("2023-12-07T06:44:41.000Z")
        ),Forum(
            4,
            "pertumbuhan tanaman",
            "gimana ya",
            R.drawable.img_dummy,
            dateFormat.parse("2023-12-07T06:44:41.000Z")),
    )
}