package com.ecostay.ui.info;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Initialize the TextView to show information
        TextView tvInfo = findViewById(R.id.tvInfo);

        // Set the content of the TextView with comprehensive info about EcoStay Retreat
        String infoText = "🌿 Welcome to EcoStay Retreat!\n\n" +
                "🏞️ NATURE RESERVES & LOCATION\n" +
                "Our retreat is nestled within the pristine Mountain Valley Nature Reserve, " +
                "home to over 200 species of birds, rare wildflowers, and ancient oak forests. " +
                "We're located adjacent to three protected nature reserves:\n" +
                "• Crystal Lake Reserve (2 miles)\n" +
                "• Eagle Peak Wilderness (5 miles)\n" +
                "• Green Valley Conservation Area (3 miles)\n\n" +
                
                "🌱 SUSTAINABILITY INITIATIVES\n" +
                "We're committed to environmental stewardship:\n" +
                "• 100% Solar Energy - Our entire facility runs on renewable energy\n" +
                "• Zero-Waste Kitchen - All food waste is composted\n" +
                "• Rainwater Harvesting - 50,000 gallon collection system\n" +
                "• Organic Garden - Fresh herbs and vegetables grown on-site\n" +
                "• Electric Vehicle Charging - Free charging for eco-conscious guests\n" +
                "• Carbon Neutral Operations - We offset all emissions\n\n" +
                
                "🏨 ECO-FRIENDLY ACCOMMODATIONS\n" +
                "• Forest View Suites - Built with reclaimed wood and natural materials\n" +
                "• Eco Pod Cabins - Ultra-efficient, minimal environmental footprint\n" +
                "• Lakeside Family Rooms - Natural ventilation and solar heating\n" +
                "• All rooms feature organic linens and eco-friendly toiletries\n\n" +
                
                "🎯 ACTIVITIES & EXPERIENCES\n" +
                "• Guided Nature Walks - Led by certified naturalists\n" +
                "• Bird Watching Tours - Spot rare species in their natural habitat\n" +
                "• Sustainability Workshops - Learn eco-friendly living practices\n" +
                "• Organic Cooking Classes - Farm-to-table culinary experiences\n" +
                "• Stargazing Sessions - Dark sky preserve for astronomy\n" +
                "• Wildlife Photography - Capture nature's beauty responsibly\n\n" +
                
                "🌍 GREEN CERTIFICATIONS\n" +
                "• LEED Platinum Certified\n" +
                "• Green Key Eco-Rating: 5 Keys\n" +
                "• Carbon Trust Certified\n" +
                "• Local Conservation Partnership Member\n\n" +
                
                "💚 SPECIAL OFFERS & EVENTS\n" +
                "• Earth Day Celebration (April 22) - 20% off all activities\n" +
                "• Summer Solstice Festival (June 21) - Free guided tours\n" +
                "• Fall Migration Watch (September) - Bird watching discounts\n" +
                "• Winter Wildlife Photography (December) - Special packages\n\n" +
                
                "📱 ECO-FRIENDLY FEATURES\n" +
                "• Digital check-in to reduce paper waste\n" +
                "• Mobile app for activity bookings\n" +
                "• Electric bike rentals for exploring\n" +
                "• Reusable water bottles provided\n" +
                "• Local artisan products in our eco-shop\n\n" +
                
                "Join us for an unforgettable sustainable experience that connects you with nature while preserving it for future generations! 🌿✨";

        // Display the information in the TextView
        tvInfo.setText(infoText);
    }
}
