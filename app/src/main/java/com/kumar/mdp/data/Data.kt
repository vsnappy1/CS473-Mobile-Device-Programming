package com.kumar.mdp.data

import com.kumar.mdp.model.Plant
import com.kumar.mdp.model.PlantCareTip

val plants = listOf(
    Plant(
        1, "Evergreen oak", "Fagaceae", 2, "09-13-2022",
        "https://d2seqvvyy3b8p2.cloudfront.net/40ab8e7cdddbe3e78a581b84efa4e893.jpg"
    ),
    Plant(
        2, "Common nettle", "Urticaceae", 3, "07-14-2022",
        "https://bs.plantnet.org/image/o/9db58cbb3538a6b77384f972971d51869228e545"
    ),
    Plant(
        3, "Barnyard grass", "Poaceae", 1, "01-15-2023",
        "https://bs.plantnet.org/image/o/f84a7d4fc2e627ccd451f568479b1932c2b2d900"
    ),
    Plant(
        4, "Narrow-leaf plantain", "Plantaginaceae", 2, "03-27-2021",
        "https://bs.plantnet.org/image/o/f8d7d6fe52e36d04f5ad1fc03f46f604d5c3cc43"
    ),
    Plant(
        5, "Milfoil", "Asteraceae", 2, "01-02-2024",
        "https://bs.plantnet.org/image/o/d788a757cd8bac8c3b1378a970c078a7a937a174"
    )
)
val plantCareTips = listOf(
    PlantCareTip("Evergreen oak","For potted plants at home, you may be unsure of how often to water them. For most plants, the golden rule is to see if the first inch or so of soil is dry. If dry, this is an indication that the plant needs water. If there are leaves that have shriveled or are dry/discolored, the plant might need a little extra water than a regular routine."),
    PlantCareTip("All","A plant can recover faster from being deprived of water than one given excess water. To rescue an overwatered plant, you may need to repot the plant and remove any unhealthy roots and overwatered soil before moving the plant into a new pot. "),
    PlantCareTip("Milfoil","Houseplants don’t require fertilization unless they are struggling to grow. If you’re unsure of how much or what type of fertilization to use when planting, it’s better to skip that step altogether. Too much fertilization may actually end up killing your plant rather than helping it."),
    PlantCareTip("Narrow-leaf plantain","We recommend scoping out where you’ll put your plant in your house before picking one out. Plants thrive once they are used to their surroundings and finding a spot with the right amount of light is important. Temperature is also important. Fluctuating temperatures will shock your plant and therefore lead to the plant not being able to develop and maybe even die. Most plants prefer temperatures of 65º–75ºF."),
    PlantCareTip("All","When buying your chosen plant, it’s always better to purchase a smaller plant over a larger one. This is because a smaller plant will be able to get more established in its home and have a larger ratio of roots to top growth. A larger plant will not continue to grow until its roots catch up with the top growth."),
    PlantCareTip("All","Low light plants still need light, but a small bathroom window with no direct light shining through is the perfect light source for these types of plants. The shower will be your plant’s main source of water as well, but not directly from the hose. The humidity produced when showering will water your plants that do not require too much watering. You may want to peek every once in a while to see if your plant needs an extra drink. This can easily be done by checking the soil, see the first tip."),
    PlantCareTip("Common nettle","When you water lightly and frequently, only the top roots are able to drink the water, and your plant may not receive the fuel it needs to survive. Watering deeply, which entails watering your plants heavily with water, allows for all the roots to grab a drink. To avoid overwatering, slowly water your plant and watch for when the water is not draining through the soil anymore. Once you notice this, stop there."),
    PlantCareTip("All","Getting rid of old-growth on your plants will help your plants grow again. This is kind of like how trimming the dead ends of your hair will help your hair grow again. After winter is when you’ll see some of the tips or leaves of your plant dying, so a simple plant haircut will do!"),
)