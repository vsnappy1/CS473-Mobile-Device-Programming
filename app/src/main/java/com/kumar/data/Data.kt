package com.kumar.data

import com.kumar.enums.Day
import com.kumar.model.MealPlan
import com.kumar.model.MealPlanOnDay
import com.kumar.model.Recipe
import com.kumar.model.User

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
    ),

)