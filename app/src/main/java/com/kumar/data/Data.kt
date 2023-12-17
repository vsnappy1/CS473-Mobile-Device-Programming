package com.kumar.data

import com.kumar.enums.Day
import com.kumar.model.AboutMe
import com.kumar.model.Blog
import com.kumar.model.MealPlan
import com.kumar.model.MealPlanOnDay
import com.kumar.model.Recipe
import com.kumar.model.User
import java.time.LocalDate
import java.time.Month

val users = mutableListOf<User>()

val recipes = mutableListOf(
    Recipe(
        name = "Chef John's Perfect Prime Rib",
        description = "Use my easy \"mathematical method\" for cooking prime rib, and you'll be rewarded with the best prime rib that's perfectly pink and delicious. For the math to work, you must leave the beef out at room temperature for at least 6 hours.",
        image = "https://www.allrecipes.com/thmb/aRiv4XBOb5z-96YZww2hJnPsObo=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/221958-chef-johns-perfect-prime-rib-DDMFS-4x3-a61d2f7572694e7cbe7dfaa623014bfc.jpg",
        cookingTime = 150,
        userRatings = 4.0
    ),
    Recipe(
        name = "Alaska Salmon Bake with Pecan Crunch Coating",
        description = "Baked salmon makes an excellent main course!",
        image = "https://www.allrecipes.com/thmb/8MVjVxa10VXlTkIUf4D7ZUGfQyM=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/126607-67f6e8abb4b141c8b2839c45dfbbe027.jpg",
        cookingTime = 30,
        userRatings = 4.2
    ),
    Recipe(
        name = "Traditional French Canadian Tourtiere",
        description = "Originally from my grandma's recipe box, the secret of this delectable Christmas treasure is found in the ground cloves and chicken seasoning. We've always made our tourtieres en grand (in large quantity), as they freeze great making them a terrific quick fix throughout the busy holiday season!",
        image = "https://www.allrecipes.com/thmb/Gep85qAvqHkrLBuYNSDT05ogz2I=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/7512105-cf0a584df04940bea2cc050cb5a2ea70.jpg",
        cookingTime = 50,
        userRatings = 4.5
    ),
    Recipe(
        name = "Tangy Honey-Glazed Ham",
        description = "I made this ham glaze recipe using ingredients I had on hand, and it's the best I've ever tasted. Brush onto the ham during the last half hour of baking to add incredible flavor and create a sweet, sticky, caramelized coating your guests will love.",
        image = "https://www.allrecipes.com/thmb/GFflSIRDGu-mjshUSuhkT3aJomY=/0x512/filters:no_upscale():max_bytes(150000):strip_icc()/24501-tangy-honey-glazed-ham-DDMFS-4x3-0a5c0cf9d6b64c7f9650205727af31b5.jpg",
        cookingTime = 155,
        userRatings = 4.8
    ),
    Recipe(
        name = "Blue Cheese and Pear Tartlets",
        description = "These tasty pear and blue cheese tartlets take little time to prepare but will impress your guests!",
        image = "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimages.media-allrecipes.com%2Fuserphotos%2F5939579.jpg&q=60&c=sc&orient=true&poi=auto&h=512",
        cookingTime = 25,
        userRatings = 4.1
    ),
    Recipe(
        name = "Taco Cheese Ball Wreath",
        description = "With a surprising taco-flavor, this rich and creamy cheese ball is shaped and decorated like a wreath so it's perfect for your holiday table. Serve with crackers, corn chips, or Fritos® Scoops®.",
        image = "https://www.allrecipes.com/thmb/tBc1A04h00-mDrSCcLsxF1vmJ7k=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Taco-Cheese-Ball-Wreath-2000-ddfab1acd72b44f182793d004410bf47.jpg",
        cookingTime = 455,
        userRatings = 3.8
    ),
)

val mealPlans = listOf(
    MealPlanOnDay(
        day = Day.MONDAY,
        mealPlan = MealPlan(
            breakfastRecipe = recipes[0]
        )
    ),
    MealPlanOnDay(
        day = Day.TUESDAY,
        mealPlan = MealPlan()
    ),
    MealPlanOnDay(
        day = Day.WEDNESDAY,
        mealPlan = MealPlan()
    ),
    MealPlanOnDay(
        day = Day.THURSDAY,
        mealPlan = MealPlan()
    ),
    MealPlanOnDay(
        day = Day.FRIDAY,
        mealPlan = MealPlan()
    ),
    MealPlanOnDay(
        day = Day.SATURDAY,
        mealPlan = MealPlan()
    ),
    MealPlanOnDay(
        day = Day.SUNDAY,
        mealPlan = MealPlan()
    )
)

val blogs = mutableListOf(
    Blog(
        username = "Emily",
        title = "My favorite Christmas Eve tradition",
        description = "I remember the first time I ever had Bûche de Noël, a flourless chocolate cake filled with a chocolate whipped cream and topped with a rich buttercream, all rolled together to resemble a log. It was Christmas Eve at our next door neighbor's home, and I'd never seen a more gorgeous dessert. That was nearly twenty years ago and for the past decade or so, I've been making it myself as part of our annual tradition during the holidays. It's a beast of a recipe, comprised of so many steps it'll make you dizzy, but none of them are necessarily difficult and I promise it's SO worth the effort. If you're up for a challenge, set aside the morning, crank up the Christmas carols, and make a yule log - just be prepared to have your family request that it become an annual tradition.",
        createOn = LocalDate.of(2022, Month.DECEMBER, 12)
    ),
    Blog(
        username = "Emily",
        title = "Leaning into my favorite time of year",
        description = "I know for a lot of people, winter is when they can find themselves feeling down, desperate for warm weather and a little sun. But as we *finally* start to enter cooler temperatures in L.A., I've been emerging from what I can only call a \"summer slump.\" I've always favored fall and winter, but hadn't realized how impacted I am when nothing but triple digits line the forecast. Luckily there's been a slight shift lately and now that I'm home after three back-to-back trips to the South (North Carolina, Georgia, and Louisiana), I'm feeling excited and ready to plan alllll the things with my friends and family. Here are a few of my favorite moments from the last few weeks.",
        createOn = LocalDate.of(2023, Month.OCTOBER, 30)
    ),
    Blog(
        username = "Nicole Prince",
        title = "In remembrance of Leo",
        description = "Trigger warning: This piece includes the author's descriptions of experiencing a miscarriage.\n" +
                "\n" +
                "I was really dreading Monday. \n" +
                "\n" +
                "We’d had the most perfect weekend; it was early May, 75 degrees in Philadelphia, and our Sunday was wide open. I was craving donuts, which I attributed to the babe growing in my belly, so my husband, toddler, and I set off in pursuit of a treat. I’m not known for my sense of direction and, true to form, I miscalculated the bakery’s location—a 15-minute walk turned into 45 minutes. But it didn’t matter—I had beautiful weather, a baby coming in the fall, and my boys, Tim and Clay. \n" +
                "\n" +
                "We stuffed ourselves with the fluffiest donuts from a hole-in-the-wall Italian bakery (the best kind) and slowly made our way back home, stopping on a tiny street so Clay could set up his toy cars and reimagine his most recent construction scene. ",
        createOn = LocalDate.of(2021, Month.FEBRUARY, 24)
    ),
    Blog(
        username = "Geoffrey",
        title = "G's Father's Day Gift Guide",
        description = "Omorpho Weighted Vest (\$299): This micro loaded weight vest adds 10lbs of weight and increases the intensity of any workout, while allowing for easy movement during any training activity.\n" +
                "\n" +
                "Raicilla (\$73): This is the lesser known cousin of tequila and mezcal, but it’s slowly emerged as my go-to spirit for making cocktails. The history of raicilla dates back centuries and its distribution and production have a fun backstory, but what makes it stand apart from Tequila and Mezcal is the distillation process and unique flavor profile created from similar agave plants. If you haven’t tried it, this bottle is a great place to start.\n" +
                "\n" +
                "ASICS Quantum 360 Sneakers (\$240): Whether you’re a dad, someone who loves trail running, or somewhere in between, these reimagined Gel Quantam Asics, designed in collaboration with London-based Kiko Kostadinov, will provide a stylish upgrade to your daily regimen. In addition to their comfort and training pedigree, the unique colorways set them apart from basic Asics.",
        createOn = LocalDate.of(2023, Month.JUNE, 14)
    )
)

val aboutMeList = mutableListOf(
    AboutMe(
        title = "Android Developer",
        description = "Hi there!!, this is Vishal Kumar an Android Developer with 5+ years on experience in Android Development"
    ),
    AboutMe(
        title = "Favorite Recipes",
        description = "1. Chicken Karahi\n" +
                "2. Biryani\n" +
                "3. Aloo Paratha"
    ),
    AboutMe(
        title = "Food Philosophy",
        description = "I prefer fresh food more than frozen one, I cook my meal with fresh vegetables"
    )
)