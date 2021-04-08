package com.jinendra.fact.Facts.Psychological.Animals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jinendra.fact.Category;
import com.jinendra.fact.MusicComponent;
import com.jinendra.fact.R;

import static com.jinendra.fact.MusicComponent.mediaPlayer;

public class AnimalsFactList extends AppCompatActivity {

    ImageView vPreviousMenuAnimalsFactListActivity;

    ListView vFactNumberListViewAnimalsFactListActivity;
    String[] vFactNumbersAnimalsFactListActivity = new String[515];
    int i,j,vFactNumberAnimalsFactListActivity,k;
    public static final String[] extraTextAnimalsFactListActivity = new String[515];
    public static final String EXTRA_NUMBER_ANIMALS_FACT_LIST_ACTIVITY = "com.jinendra.fact.Facts.Psychology.Animals.EXTRA_NUMBER_ANIMALS_FACT_LIST_ACTIVITY";
    String[] animalsFacts = {"#1 Not all species of shark give birth to live pups. Some species lay the egg case on the ocean floor and the pup hatches later on its own.",
            "#2 Only 5% of cheetah cubs survive to adulthood.",
            "#3 Tigers can easily jump over 5 metres in length.",
            "#4 Giraffes have bluish-purple tongues which are tough and covered in bristly hair to help them with eating the thorny Acacia trees.",
            "#5 Lions are the second largest big cat species in the world (behind tigers).",
            "#6 The howler monkey is the loudest land animal. Its calls can be heard from 3 miles (5 km) away.",
            "#7 A giraffe’s tongue is so long that it uses it to clean its ears. The colour of the tongue is black. This is one of the weird animal facts about the giraffe.",
            "#8 The Killer Whale (also known as Orca) is actually a type of dolphin.",
            "#9 Sperm whales in the Caribbean have an accent.",
            "#10 When the pack kills an animal, the alpha pair always eats first. As food supply is often irregular for wolves, they will eat up to 1/5th of their own body weight at a time to make up for days of missed food.",
            "#11 Butterflies taste with their feet.",
            "#12 Male horses have way more teeth than their female counterparts.",
            "#13 In seahorses, the male gives birth to a young one.",
            "#14 Sharks do not have a single bone in their bodies. Instead they have a skeleton made up of cartilage; the same type of tough, flexible tissue that makes up human ears and noses.",
            "#15 Dogs' sense of smell is about 100,000 times stronger than humans', but they have just one-sixth our number of taste buds.",
            "#16 Elephants and humans have similar self-soothing techniques.",
            "#17 Female dolphins are called cows, males are called bulls and young dolphins are called calves.",
            "#18 A male giraffe can weigh as much as a pick up truck! That’s about 1400 kilograms.",
            "#19 Many animals have been reported to commit suicide, including cows, dogs, bulls, and sheep.",
            "#20 One way to always recognise a cheetah is by the long, black lines which run from the inside of each eye to the mouth. These are usually called “tear lines” and scientists believe they help protect the cheetah’s eyes from the harsh sun and help them to see long distances.",
            "#21 Zebra stripes act as a natural bug repellent.",
            "#22 Domestic dogs are omnivores, they feed on a variety of foods including grains, vegetables and meats.",
            "#23 Tigers not only have stripes on their fur, they also have them on their skin. No two tigers ever have the same stripes like two humans can't ever have the same fingerprints.",
            "#24 Some dolphin species face the threat of extinction, often directly as a result of human behaviour. The Yangtze River Dolphin is an example of a dolphin species which may have recently become extinct.",
            "#25 Leopards communicate with each other through distinctive calls. For instance, when a male wants to make another leopard aware of his presence, he’ll make a hoarse, raspy cough. They also growl when angry and, like domestic cats, purr when happy and relaxed. Cute, eh?",
            "#26 Honeybees can count, categorize similar objects like dogs or human faces, understand same and different and differentiate between shapes that are symmetrical and asymmetrical.",
            "#27 Two thirds(2/3) of a cat’s life is spent asleep.",
            "#28 Cute animals are more likely to be endangered.",
            "#29 A cheetah has amazing eyesight during the day and can spot prey from 5 km away.",
            "#30 Female bats give birth to babies that weigh up to a third of their weight.",
            "#31 Leopards are very solitary and spend most of their time alone. They each have their own territory, and leave scratches on trees, urine scent marks and poop to warn other leopards to stay away! Males and females will cross territories, but only to mate.",
            "#32 Instead of bones, sharks have a skeleton made from cartilage.",
            "#33 Whales that are found in both Northern and Southern hemisphere never meet or breed together. Their migration is timed so that they are never in breeding areas at the same time.",
            "#34 About 70% of the world’s spices come from India.",
            "#35 Giant anteaters consume up to 35,000 ants and termites in a single day.",
            "#36 Great whites are the deadliest shark in the ocean. These powerful predators can race through the water at 30 km per hour.",
            "#37 The most venomous fish in the world is the 30cm Stonefish.",
            "#38 The smell of a skunk is powerful enough for a human to smell it up to 3.5 miles (5.6 km) away.",
            "#39 Some starfish can regenerate a new body from a single severed arm. While the regenerative abilities of starfish vary from species to species, most of these impressive invertebrates can at least re-grow a limb that’s been cut off. Some sea stars can fully generate out of a single arm.",
            "#40 Most leopards are light coloured and have dark spots on their fur. These spots are called “rosettes” because their shape is similar to that of a rose. There are also black leopards, too, whose spots are hard to see because their fur is so dark.",
            "#41 Other than humans, emperor penguins are the only warm-blooded animal to stay on Antarctica for the winter.",
            "#42 Reindeer live in the Arctic tundra and damp forests of Greenland, Scandinavia, Russia, Alaska and Canada. In North America, reindeer are known as caribou – pronounced ‘kar-i-boo!’",
            "#43 Some Tiny Spiders Wear Water Droplets As Hats",
            "#44 Animals have friends. Some studies indicate that cows do indeed have ‘best friends’ and they even show the signs of stress when they are separated from their beastie.",
            "#45 Giant water lilies in the Amazon can grow over 6 feet in diameter.",
            "#46 Cows painted with zebra-like stripes can avoid being bitten by flies.",
            "#47 Ravens are masters of deception.",
            "#48 Frogs use their sticky, muscular tongue to catch and swallow food. Unlike humans, their tongue is not attached to the back of its mouth. Instead it is attached to the front, enabling the frog to stick its tongue out much further.",
            "#49 There are more than 1.4 billion insects for EACH HUMAN on the planet, according to recent estimates.",
            "#50 Koala fingerprints are so close to humans' that they could taint crime scenes.",
            "#51 Cheetahs are the only big cat that cannot roar. They can purr though and usually purr most loudly when they are grooming or sitting near other cheetahs.",
            "#52 There is an average of 50,000 spiders per acre in green areas.",
            "#53 Reindeer can live for up to 15 years in the wild.",
            "#54 The most popular breed of dog in the world by registered ownership is the Labrador. With their gentle nature, obedience, intelligence and near limitless energy, Labradors make for excellent family pets and reliable workers. They often assist police and are a common choice as guide dogs.",
            "#55 Because of the unusual shape of their legs, kangaroos and emus struggle to walk backwards.",
            "#56 Wolves have two layers of fur, an undercoat and a top coat, which allow them to survive in temperatures as low at minus 40 degrees Celsius! In warmer weather they flatten their fur to keep cool.",
            "#57 The Nile crocodiles jaws can apply 5,000 pounds of pressure per square inch – the strongest bite of any animal in the world.",
            "#58 Elephants have large, thin ears. Their ears are made up of a complex network of blood vessels which help regulate their temperature. Blood is circulated through their ears to cool them down in hot climates.",
            "#59 The dementor wasp paralyses cockroaches with venom to its head, turning them into a zombie-like state. The toxins leave the cockroach unable to control its own movements, which incredibly makes it run into the wasps nest to meet its demise.",
            "#60 Panda’s must eat 25 – 90 pounds (12-38 kg) of bamboo every day to meet their energy needs.",
            "#61 Wolves are excellent hunters and have been found to be living in more places in the world than any other mammal except humans.",
            "#62 Some lipsticks contain fish scales.",
            "#63 A giraffe's habitat is usually found in African savannas, grasslands or open woodlands.",
            "#64 The domestic dog has been one of the most popular working and companion animals throughout human history.",
            "#65 Sea otters have the densest fur of any mammal: a large male has around 800 million hairs, compared to just five million on a human.",
            "#66 Less than 100 years ago, tigers could be found throughout Asia.",
            "#67 Did you know that the rabbit's teeth never stop growing? These mammals need something to constantly gnaw on, this is the only way they can wear out their teeth, which never stop growing",
            "#68 An elephant’s trunk can grow to be about 2 metres long and can weigh up to 140 kg. Some scientists believe that an elephant’s trunk is made up of 100,000 muscles, but no bones.",
            "#69 Some Fish Can Recognize Their Owner’s Face.",
            "#70 Rats breed so quickly that in just 18 months, 2 rats could have created over 1 million relatives. ",
            "#71 Dolphins are another species that are often focused on for their smarts. A recent finding that exemplifies this is that dolphins have names for one another (which form when other dolphins mimic the sounds they make) – and recognize their own title when it is called by other dolphins.",
            "#72 Most of the dust in your home is actually dead skin! Yuck!",
            "#73 Emperor penguins can stay underwater for up to 27 minutes and dive as far as 500m deep.",
            "#74 The little known pangolin is the worlds most poached and trafficked animal.",
            "#75 Crocodiles can live up to 100.",
            "#76 Dolphins Have Names For One Another",
            "#77 Elephants are scared of bees. Elephants only sleep 2 to 3 hours each day. An elephant can smell water from 12 miles away. One of the most interesting fun facts about elephants is that they remain pregnant for 2 years.",
            "#78 Cows Have “Best Friends” And Are Happier When They’re With Them.",
            "#79 Goats have rectangular pupils in their eyes.",
            "#80 Cheetahs cannot climb trees and have poor night vision.",
            "#81 An eagle’s eyes are at least four times sharper than a human’s.",
            "#82 In the wild, lions rest for around 20 hours a day.",
            "#83 Owls have three eyelids on each eye: one for blinking, one for sleeping, and one for cleansing.",
            "#84 Dog have superior hearing than humans, capable of hearing sounds at four times the distance.",
            "#85 A cheetah can accelerate from 0 to 113 km in just a few seconds.",
            "#86 Gorillas are endangered animals. Their habitat is destroyed when people use the land for farming and the trees for fuel. Gorillas are also killed by poachers and sometimes get caught in poacher’s snares meant for other animals.",
            "#87 Whales support many different types of life. Several creatures, such as barnacles and sea lice, attach themselves to the skin of whales and live there.",
            "#88 Horses and cows sleep while standing up.",
            "#89 Axolotls can regenerate their parts.",
            "#90 Birds can not live in space – they need gravity or they can not swallow.",
            "#91 Tigers have striped skin.",
            "#92 Giraffes have black tongues.",
            "#93 At full speed, tigers can reach up to 65km/h.",
            "#94 Cows have best friends.",
            "#95 Ducks can surf.",
            "#96 Rats like to be tickled. High-pitched chirping is how they laugh, and some rats prefer being tickled to eating.",
            "#97 The longest living, verified animal is a Madagascar radiated tortoise, which died at an age of 188 years in May 1965.",
            "#98 Cows can walk up stairs, but not down them.",
            "#99 Male dogs prefer to play with female dogs, whereas female dogs do not discriminate between playmates. This may arise from an evolutionary necessity for mothers to care for pups of both genders.",
            "#100 Cat urine glows under a black-light.",
            "#101 Dolphins live in schools or pods of up to 12 individuals.",
            "#102 Narwhal tusks are really an \"inside out\" tooth.",
            "#103 Penguins Not Only Mate For Life, They Will Also Spend Time To Find A Beautiful Pebble For Their Mate And Then “Propose” With It",
            "#104 Despite the white, fluffy appearance of Polar Bears fur (which is transparent), it actually has black skin.",
            "#105 Dogs have formed such a strong bond as pets, workers and companions to humans that they have earned the nickname \"man's best friend\".",
            "#106 Tigers have striped skin, not just striped fur.",
            "#107 Flying ants are just normal ants – with wings!",
            "#108 Cats recognize their owner's voice, but often don't care.",
            "#109 Prairie Dogs Say Hello By Kissing.",
            "#110 Slugs have four noses.",
            "#111 Male lions are easy to recognize thanks to their distinctive manes. Males with darker manes are more likely to attract female lions (lionesses).",
            "#112 Cats use their whiskers to check whether a space is too small for them to fit through or not.",
            "#113 Queen mole rats make other female mole rats infertile.",
            "#114 Hummingbirds are the only known birds that can also fly backwards.",
            "#115 Remarkably, frogs actually use their eyes to help them swallow food. When the frog blinks, its eyeballs are pushed downwards creating a bulge in the roof of its mouth. This bulge squeezes the food inside the frog's mouth down the back of its throat.",
            "#116 Guinea Pigs Hop Up And Down When Excited And Apparently It’s Called “Popcorning”.",
            "#117 Elephants are the world’s largest land animal! Male African elephants can reach 3m tall and weigh between 4,000 -7,500kg. Asian elephants are slightly smaller, reaching 2.7m tall and weighing 3,000– 6,000kg.",
            "#118 Squirrels will adopt orphans.",
            "#119 Drinking is one of the most dangerous times for a giraffe. While it is getting a drink it cannot keep a look out for predators and is vulnerable to attack.",
            "#120 Many subspecies of the tiger are either endangered or already extinct. Humans are the primary cause of this through hunting and the destruction of habitats.",
            "#121 Humans share 98.8% of chimpanzee DNA. But even with DNA so similar, humans and chimps have around 35 million differences between them.",
            "#122 If a snail doesn't like the weather, it can hide in its shell for up to three years.",
            "#123 The average male lion weighs around 180 kg (400 lb) while the average female lion weighs around 130 kg (290 lb).",
            "#124 When dogs look their human companions in the eye, it may be a look of love, rather than simply begging.",
            "#125 The Edible Dormouse (Glis glis) is able to hibernate the longest – up to 11-months of the year when food availability is low.",
            "#126 Some fish cough! Really.",
            "#127 Baby pandas are born pink and measure about 15cm – that’s about the size of a pencil! They are also born blind and only open their eyes six to eight weeks after birth.",
            "#128 Elephants can smell water from a dozen miles away. Humans subdue (tame, train) elephants like slaves, including immobilizing them and depriving them of food and water for up to three weeks. Rarely, an elephant will commit suicide, shutting off its own air supply by stepping on its trunk and refusing to budge.",
            "#129 Ducks Like To Surf. They’ve Been Observed Riding Tides And Swimming Back To Ride Them Again.",
            "#130 A giraffe rarely lays down, they even sleep and give birth standing up. Giraffes are the only animals born with horns. They are born with bony k***s on their forehead.",
            "#131 A shark is the only known fish that can blink with both eyes.",
            "#132 Leopards are fast felines and can run at up to 58km/h! They’re super springy, too, and can leap 6m forward through the air – that’s the length of three adults lying head to toe!",
            "#133 Sharks have outstanding hearing. They can hear a fish thrashing in the water from as far as 500 metres away!",
            "#134 A frog is an amphibian. They lay their eggs in water. The eggs hatch into a tadpole which lives in water until it metamorphoses into an adult frog.",
            "#135 Otters have the world's thickest fur.",
            "#136 A tiger’s roar can be heard as far as three kilometres away.",
            "#137 Dragonflies And Damselflies Form A Heart With Their Tails When They Mate",
            "#138 The largest living animal is the blue whale, which can measure as much as 100 feet.",
            "#139 Subspecies of the tiger include the Sumatran Tiger, Siberian Tiger, Bengal Tiger, South China Tiger, Malayan Tiger and Indochinese Tiger.",
            "#140 The loudest animal in the world is a mere 2cm long, prawn. The Pistol Shrimp is capable of snapping it’s claw shut so rapidly, that it creates a bubble which collapses to produce a sonic blast, louder than a Corncode’s sonic boom.",
            "#141 An elephant can use its tusks to dig for ground water. An adult elephant needs to drink around 210 litres of water a day.",
            "#142 Great white sharks can detect a drop of blood in 25 gallons (100 litres) of water and can even sense tiny amounts of blood from 3 miles (5 km) away.",
            "#143 When dogs look at their human companions in the eye, it may actually be a look of love, rather than simple begging.",
            "#144 A group of rhinos is called a crash.",
            "#145 Locusts have leg muscles that are about 1000 times more powerful than an equal weight of human muscle.",
            "#146 Pigs’ orgasms last for 30 minutes.",
            "#147 Honey bees are super-important pollinators for flowers, fruits and vegetables. This means that they help other plants grow! Bees transfer pollen between the male and female parts, allowing plants to grow seeds and fruit.",
            "#148 In total there is said to be around 400 million dogs in the world.",
            "#149 Spiders can’t fly.",
            "#150 Tigers have been known to reach speeds up to 65 kph (40 mph).",
            "#151 Every Chinese New Year starts a new animal's zodiac year.",
            "#152 Around 50 percent of orangutans have fractured bones, due to falling out of trees on a regular basis.",
            "#153 Gorillas are herbivores. They spend most of their day foraging for food and eating bamboo, leafy plants and sometimes small insects. Adult gorillas can eat up to 30 kilograms of food each day.",
            "#154 Macaques in Japan use coins to buy vending machine snacks.",
            "#155 Bats always turn left when leaving a cave.",
            "#156 A hippopotamus can run faster than a man.",
            "#157 Hummingbirds are so agile and have such good control that they can fly backwards.",
            "#158 Butterflies taste food by standing on top of it! Their taste receptors are in their feet unlike humans who have most on their tongue.",
            "#159 Dogs have a remarkable sense of smell, they are capable of differentiating odours in concentrations nearly 100 million times lower than humans can.",
            "#160 The fingerprints of a koala are so indistinguishable from humans that they have on occasion been confused at a crime scene.",
            "#161 Rare white tigers carry a gene that is only present in around 1 in every 10000 tigers.",
            "#162 Some sharks remain on the move for their entire lives. This forces water over their gills, delivering oxygen to the blood stream. If the shark stops moving then it will suffocate and die.",
            "#163 Tigers are carnivores, eating only meat.",
            "#164 Alligators can grow for more than 30 years.",
            "#165 The way a dog’s tail wags – right or left – seems to have different meanings as a non-verbal communicator (although contradicting interpretations exist).",
            "#166 In the wild, lions live for an average of 12 years and up to 16 years. They live up to 25 years in captivity.",
            "#167 There’s An Abandoned Minefield That Has Accidentally Created A Penguin Sanctuary. The Mines Keep Poachers Out, But The Penguins Are Too Small To Set Them Off.",
            "#168 Rabbits are amazing athletes — they can jump as high as 90 centimetres in one leap!",
            "#169 Puffins mate for life. They make their homes on cliff sides and set aside room for their toilet.",
            "#170 Roosters prevent themselves from going deaf due to their own loud crowing, by tilting their head backs when they crow, which covers their ear canal completely, serving as a built-in ear-plug.",
            "#171 Dolphins have names for one another.",
            "#172 Giraffes with darker spots are more dominant.",
            "#173 Hundreds Of Trees Become Seedlings Every Year Because Of Squirrels Forgetting Where They Buried Their Food.",
            "#174 A pup (baby shark) is born ready to take care of itself. The mother shark leaves the pup to fend for itself and the pup usually makes a fast get away before the mother tries to eat it!",
            "#175 When they find a source of water, wild Bactrian (two-humped) camels will drink as much as 50 litres (88 pints). They will even drink salty water, unlike other mammals.",
            "#176 A housefly buzzes in an F key.",
            "#177 A Blue Whales tongue can weigh as much as a car, or a small adult elephant.",
            "#178 Less than 10% of hunts end successfully for tigers.",
            "#179 An octopus has three hearts, nine brains and blue blood.",
            "#180 Unlike humans, sheep have four stomachs, each one helps them digest the food they eat.",
            "#181 Dogs make flirting easier.",
            "#182 Bottlenose dolphins are the most common and well known type of dolphin.",
            "#183 Some fishing methods, such as the use of nets, kill a large number of dolphins every year.",
            "#184 Young male gorillas usually leave their family group when they are about 11 years old and have their own family group by the age of 15 years old. Young female gorillas join a new group at about 8 years old.",
            "#185 Domestic cats love to play, this is especially true with kittens who love to chase toys and play fight. Play fighting among kittens may be a way for them to practice and learn skills for hunting and fighting.",
            "#186 Mosquitoes can be annoying insects but did you know that it's only the female mosquito that actually bites humans.",
            "#187 A dog’s nose is like a human finger print – unique to its owner.",
            "#188 Animals like Worms, Birds, Cats and Dogs have the ability to sense earthquakes before they take place. Especially some insects, like worms, are even capable of predicting earthquakes a week before.",
            "#189 The fastest land animal on the planet is a cheetah. It can reach speeds of up to 75 mph (120 kph).",
            "#190 Vampire bat saliva keeps blood from clotting.",
            "#191 Most lions found in the wild live in southern and eastern parts of Africa.",
            "#192 A wolf can run at a speed of 65 kilometres per hour during a chase. Wolves have long legs and spend most of their time trotting at a speed of 12-16 kilometres per hour. They can keep up a reasonable pace for hours and have been known to cover distances of 90 kilometres in one night.",
            "#193 Chess makes kids smart. It forces students to slow down, concentrate, use precise thinking, active both inductive and deductive reasoning, as well as recognizing difficult and complex patterns.",
            "#194 A tigers rear legs are so powerful, that they have been found remaining to stand even after death",
            "#195 Even when a snake has its eyes closed, it can still see through its eyelids.",
            "#196 Maine is the only state that has a one-syllable name.",
            "#197 Female elephants are called cows. They start to have calves when they are about 12 years old and they are pregnant for 22 months.",
            "#198 Ostriches can run faster than horses, and the male ostriches can roar like lions.",
            "#199 Seahorses mate for life, and when they travel, they hold each others’ tails.",
            "#200 Whales love to sing! They use this as a call to mates, a way to communicate and also just for fun! After a period of time they get bored of the same whale song and begin to sing a different tune.",
            "#201 Play is costly in terms of energy, but evolution has made it such fun that mammals spend between 1 and 10 per cent of their time at play.",
            "#202 Lions are the national animal of Albania, Belgium, Bulgaria, England, Ethiopia, Luxembourg, the Netherlands and Singapore",
            "#203 Lions can reach speeds of up to 81 kph (50 mph) but only in short bursts because of a lack of stamina.",
            "#204 Wild chimpanzees enjoy getting tipsy.",
            "#205 Adult leopards are solitary animals. Each adult leopard has its own territory where it lives and, although they often share parts of it, they try to avoid one another.",
            "#206 Dolphins communicate with each other by clicking, whistling and other sounds.",
            "#207 Compared to other animals, dolphins are believed to be very intelligent.",
            "#208 Squirrels will adopt other squirrels babies if they are abandoned.",
            "#209 An ostrich legs are so powerful that their kicks can kill a lion.",
            "#210 Even after having its head cut off, a cockroach can still live for weeks.",
            "#211 In the winter time reindeer grow their facial hair long enough to cover their mouths, which protects their muzzles when grazing in the snow. Beard-os!",
            "#212 Feral cats are often seen as pests and threats to native animals.",
            "#213 The oldest evidence of domesticated cats dates back 9,500 years.",
            "#214 Honey bees are also brilliant boogiers! To share information about the best food sources, they perform their ‘waggle dance’.",
            "#215 The pattern of wrinkles on a gorilla’s nose is unique to each one and is known as a ‘nose print’. Conservation workers use photos and sketches of gorillas’ noses to keep track of individuals.",
            "#216 Giant pandas grow to between 1.2m and 1.5m, and weigh between 75kg and 135kg. Scientists aren’t sure how long pandas live in the wild, but in captivity they live to be around 30 years old.",
            "#217 The worlds deadliest animal isn’t a shark, bear or tiger, but something far smaller – the mosquito. According to the World Health Organization, 725,000 people are killed each year from mosquito-borne diseases, such as Malaria, dengue fever and yellow fever.",
            "#218 Sea Lions have rhythm.  They are the only animal known to be able to clap in beat.",
            "#219 Red Pandas Use Their Fluffy Tails As Blankets To Keep Warm When They Sleep.",
            "#220 Frogs in the wild face many dangers and are lucky to survive several years. In captivity however, frogs can live for much longer.",
            "#221 Flying ants are also called alates.",
            "#222 Giraffes are ruminants. This means that they have more than one stomach. In fact, giraffes have four stomachs, the extra stomachs assisting with digesting food.",
            "#223 Dogs nose prints are as unique as human fingerprints and can be used to identify them.",
            "#224 The ostrich egg is the biggest in the world. It equals to the volume of as much as 30 chicken eggs.",
            "#225 Male giraffes sometimes fight with their necks over female giraffes. This is called “necking”. The two giraffes stand side by side and one giraffe swings his head and neck, hitting his head against the other giraffe. Sometimes one giraffe is hit to the ground during a combat.",
            "#226 If a starfish is split into five pieces, so long as each piece contains part of the central disc, then five starfish will survive.",
            "#227 Octopuses and hagfish have more than one heart!",
            "#228 These fierce felines have walked the earth for a long time",
            "#229 Elephants have no natural predators. However, lions will sometimes prey on young or weak elephants in the wild. The main risk to elephants is from humans through poaching and changes to their habitat.",
            "#230 There are no male or female earthworms. All earthworms have both male and female parts – but it still takes two of them to reproduce.",
            "#231 A female lion needs 5kg of meat a day. A male needs 7kg or more a day.",
            "#232 Dogs Sneeze When Play Fighting To Show They Are Playing And Don’t Want to Hurt You",
            "#233 The average life span for a dog is around 10 to 14 years.",
            "#234 There are only about 700 mountain gorillas and they live high in the mountains in two protected parks in Africa. Lowland gorillas live in central Africa.",
            "#235 Dolphins often display a playful attitude which makes them popular in human culture. They can be seen jumping out of the water, riding waves, play fighting and occasionally interacting with humans swimming in the water.",
            "#236 A cat's meow is a form of communication with humans only, not other cats.",
            "#237 Some pigs in China are the size of bears.",
            "#238 A leopard’s body is built for hunting. They have sleek, powerful bodies and can run at speeds of up to 57 kilometres per hour. They are also excellent swimmers and climbers and can leap and jump long distances.",
            "#239 Ravens Can Remember Faces And You Can Befriend Them.",
            "#240 In China, killing a Panda is punishable by death.",
            "#241 The blue whale can produce the loudest sound of any animal. At 188 decibels, the noise can be detected over 800 kilometres away.",
            "#242 Kangaroos can not walk backwards.",
            "#243 Although experts often disagree, there is scientific evidence which shows that the domestication of dogs could have occurred more than 15,000 years ago.",
            "#244 Cheetahs are carnivores and live off other animals they find on Africa’s plains, including rabbits, warthogs, springboks, gazelles and birds.",
            "#245 What is the largest flower in the world? Rafflesia Arnoldii. It can grow as big as an umbrella.",
            "#246 Turtles can breathe through their butts.",
            "#247 Baby Elephants Suck On Their Trunk For Comfort.",
            "#248 The largest living organism in the world is a fungus, it is in Oregon, covering 2,200 acres and is still growing.",
            "#249 Flamingos are not pink. They are born grey, their diet of brine shrimp and blue green algae contains a natural pink dye called canthaxanthin that makes their feathers pink.",
            "#250 Although frogs live on land their habitat must be near swamps, ponds or in a damp place. This is because they will die if their skin dries out.",
            "#251 Chinchilla Fur Is So Dense That It’s Very Bad For Them To Get Wet. So Instead Of Bathing In Water, They Bathe In Dust.",
            "#252 A prison in Washington pairs up “death row” shelter cats with select inmates as part of a rehabilitation program. It seems to be a pretty incredible thing for both the inmates and the cats.",
            "#253 There's a kind of ant that only lives in a small area of Manhattan.",
            "#254 An amphibian can live both on land and in water.",
            "#255 If a Donkey and a Zebra have a baby, it is called a Zonkey.",
            "#256 Dolphins have excellent eyesight and hearing as well as the ability to use echolocation for finding the exact location of objects.",
            "#257 Leopards protect their food from other animals by dragging it high up into the trees. A leopard will often leave their prey up in the tree for days and return only when they are hungry!",
            "#258 Dolphins use a blowhole on top of their heads to breathe.",
            "#259 Alligators will let manatees swim ahead of them.",
            "#260 A Rhinoceros‘s horns are made of ‘keratin’, the same type of protein that makes up hair and fingernails.",
            "#261 Animals & Pets can decrease our feelings of loneliness and isolation by providing companionship to all generations and lift our mood.",
            "#262 Giraffes are the tallest land animal in the world, reaching heights of 19ft (5.8 m). The ostrich is the world’s tallest bird. It can grow up to 9 feet (2.7m) tall.",
            "#263 Although most species of shark are less than one metre long, there are some species such as the whale shark, which can be 14 metres long.",
            "#264 Tigers Are Unable To Purr, So In Order To Show Affection, They Close Their Eyes And “Chuff” Because Closing Their Eyes Means They Leave Themselves Vulnerable.",
            "#265 Cats are one of, if not the most, popular pet in the world.",
            "#266 Brazil is home to 3,172 of them, making it the country with the most animals. Of those species, 383 of them are endangered.",
            "#267 Insects such as bees, mosquitoes and cicadas make noise by rapidly moving their wings.",
            "#268 When hunting alone, the wolf catches small animals such as squirrels, hares, chipmunks, raccoons or rabbits. However, a pack of wolves can hunt very large animals like moose, caribou and yaks.",
            "#269 Cows produce more milk when listening to slow music.",
            "#270 Caribbean Sperm Whales Have Their Own Regional Accent.",
            "#271 Japanese Macaques play with snowballs for fun.",
            "#272 Olphins use toxic pufferfish to ‘get high’.",
            "#273 Female elephants spend their entire lives living in large groups called herds. Male elephant leave their herds at about 13 years old and live fairly solitary lives from this point.",
            "#274 Snails take the longest naps, some lasting as long as three years.",
            "#275 Goldfishes not only listen to music, but they also can distinguish one composer from another.",
            "#276 A hippopotamus may seem huge but it can still run faster than a man.",
            "#277 Cat fleas can jump to a height of up to 60 times their own body length.",
            "#278 Leopards are skilled climbers, and like to rest in the branches of trees during the day. They are strong beasts, too, and can carry their heavy prey up into the trees so that pesky scavengers, such as hyenas, don’t steal their meal!",
            "#279 Octopuses can taste with their arms.",
            "#280 The Giant Pacific Octopus has 3 hearts, 9 brains and blue blood.",
            "#281 An adult male gorilla is called a silverback because of the distinctive silvery fur growing on their back and hips. Each gorilla family has a silverback as leader who scares away other animals by standing on their back legs and beating their chest!",
            "#282 Squirrels plant thousands of new trees each year by merely forgetting where they put their acorns.",
            "#283 Lions run at a speed of up to 81kmph.",
            "#284 Looking at cute animal pictures at work can make you more productive, a study claims.",
            "#285 Male reindeer can grow up to 1.2 metres tall at the shoulder and weigh up to 250 kilograms – that’s over three times the weight of an average person! Females are a little smaller than males.",
            "#286 Turritopsis nutricula Immortal jellyfish is the only species known to live forever.",
            "#287 The elephant’s trunk is able to sense the size, shape and temperature of an object. An elephant uses its trunk to lift food and suck up water then pour it into its mouth.",
            "#288 Cows Take Turns In Babysitting Their Young. One Will Stay With The Calves While Other Moms Graze Further Away.",
            "#289 Horses have distinct facial expressions.",
            "#290 Great white sharks have such a strong sense of smell that they can detect a colony of seals two miles away. And check this out – if there was only one drop of blood in 100 litres of water, a great white would smell it!",
            "#291 Dolphins are carnivores (meat eaters).",
            "#292 A female giraffe gives birth while standing up. The calf drops approximately 6 feet to the ground, but it is not hurt from the fall.",
            "#293 Oysters can change gender depending on which is best for mating.",
            "#294 Barking is a self-rewarding activity. This means it can be difficult to stop because a dog’s bark usually makes something happen.",
            "#295 Swifts spend most of their lives flying in the air, and can fly for almost an entire year, without ever landing.",
            "#296 Perhaps as a result, dogs who are regularly walked are also more likely to be adopted from a shelter.",
            "#297 Dolphins have been seen wrapping sea sponge around their long snouts to protect them from cuts while foraging for food.",
            "#298 Dragonflies create a heart with their tails while mating.",
            "#299 A female house fly lays batches of around 100–150 white eggs, and may lay more than 500 eggs in her lifetime of just a few days.",
            "#300 A group of parrots is known as a pandemonium.",
            "#301 Most wolves weigh about 40 kilograms but the heaviest wolf ever recorded weighed over 80 kilograms!",
            "#302 Play-based learning activities increases a child’s attention span.",
            "#303 Giant anteaters have two-foot tongues.",
            "#304 Otters “hold hands” while sleeping, so they don’t float away from each other.",
            "#305 A group of cats is called a clowder, a male cat is called a tom, a female cat is called a molly or queen while young cats are called kittens.",
            "#306 Did you know that dolphins do not chew with their teeth?  They only use them to emit sound, because when feeding they prefer to swallow food in a single gulp.",
            "#307 Baby elephants self-soothe by sucking their trunk.",
            "#308 Great white sharks can be found throughout the world’s oceans, mostly in cool waters close to the coast.",
            "#309 It is possible to identify the sex of the giraffe from the horns on its head. Both males and females have horns but the females are smaller and covered with hair at the top. Male giraffes may have up to 3 additional horns.",
            "#310 Pandas are BIG eaters – every day they fill their tummies for up to 12 hours, shifting up to 12 kilograms of bamboo!",
            "#311 Sun bear is also known as “Dog Bear” due to its glossy fur, short snout, and small size. They bark to scare away predators and to declare territory.",
            "#312 A group of tigers is known as an ‘ambush’ or ‘streak’.",
            "#313 Snakes smell with their tongue. They have ears inside their heads. Some snakes can survive without a meal for up to two years. Snakes kill 10,000 people every year. Snakes don’t have eyelids.",
            "#314 Dolphin Mothers Sing To Their Babies While They’re In The Womb.",
            "#315 A sun bear claws grow throughout its lifetime and the length of its claws can recognize the age of sun bears.",
            "#316 A female red kangaroo has three vaginas. When two kangaroos meet for the first time, they like to touch their noses and sniff each other. Males smell the urine of females to see if she is ready to mate.",
            "#317 Tigers usually hunt alone at night time.",
            "#318 The roar of a lion can be heard from 8 kilometres (5.0 miles) away.",
            "#319 The Kangaroo’s ancestors lived in trees. Today there are eight different kinds of tree kangaroos. Joeys pee and poop in their mother’s pouch, which she cleans with her paws and mouth.",
            "#320 Lionesses are better hunters than males and do most of the hunting for a pride.",
            "#321 The Great Barrier Reef in Australia is the world’s largest reef system.",
            "#322 The fastest land animal in the world, a cheetah can reach 112km/h in just three seconds – that’s faster than a sports car accelerates! Its body has evolved for speed, with long legs, an elongated spine, adapted claws to grip the ground and a long tail for balance.",
            "#323 There are hundreds of different breeds of dogs.",
            "#324 Great white sharks are grey with a white underbelly, from where they get their name. They have a streamlined shape and powerful tails that propel them through the water at over 60km per hour!",
            "#325 The common pond frog is ready to breed when it is only three years old.",
            "#326 Cats have flexible bodies and teeth adapted for hunting small animals such as mice and rats.",
            "#327 The Inland Taipan (also known as, the Western Taipan) is the most venomous snake in the world. A single bite contains enough venom to kill at least 100 fully grown men, and can kill within just 30 minutes, if left untreated.",
            "#328 Huskies can run at speeds of around 31km per hour (20mph), but their key skill is endurance.",
            "#329 The unicorn is the national animal of Scotland.",
            "#330 An Otter Will Find A Pebble As A Juvenile And Keep It For Their Whole Life.",
            "#331 While lions and leopards usually do their hunting at night, cheetahs hunt for food during the day.",
            "#332 The average worker bee lives for just five to six weeks. During this time, she’ll produce around a twelfth of a teaspoon of honey.",
            "#333 A cat has 32 muscles in each ear. All the better for them to eavesdrop on your conversations and plot your demise.",
            "#334 Each bee has 170 odorant receptors, which means they have one serious sense of smell! They use this to communicate within the hive and to recognise different types of flowers when looking for food.",
            "#335 Kangaroos use their tails for balance, so if you lift a kangaroo’s tail off the ground, it can’t hop.",
            "#336 The distinctive spots that cover a giraffe’s fur act as a good camouflage to protect the giraffe from predators. When the giraffe stands in front of trees and bushes the light and dark colouring of its fur blends in with the shadows and sunlight.",
            "#337 On average cats live for around 12 to 15 years.",
            "#338 Cheetahs are extremely fast however they tire quickly and can only keep up their top speed for a few minutes before they are too tired to continue.",
            "#339 A cat has 32 muscles in each ear.",
            "#340 Giant pandas (often referred to as simply “pandas”) are black and white bears. In the wild, they are found in thick bamboo forests, high up in the mountains of central China",
            "#341 Female leopards give birth any time of the year – when they do, they usually give birth to two or three cubs. Mothers stay with their cubs until they are about two years old, when they are old enough to hunt and take care of themselves.",
            "#342 The Alpine Swift is able to stay airborne for over 6 months without touching down.",
            "#343 You may have seen baby gorillas being carried on the back of their mothers, but for the first few months after birth the mother holds the baby gorilla to her chest.",
            "#344 To hover, hummingbirds may beat their wings up to 200 times per second.",
            "#345 Many whales are toothless. They use a plate of comb-like fibre called baleen to filter small crustaceans and other creatures from the water.",
            "#346 Koalas sleep for 22 hours a day.",
            "#347 Hippos’ closest living relatives are the aquatic mammals: whales, dolphins and porpoises.",
            "#348 When playing with female puppies, male puppies will often let them win, even if they have a physical advantage.",
            "#349 Adult wolves have large feet. A fully grown wolf would have a paw print nearly 13 centimetres long and 10 centimetres wide.",
            "#350 The average housefly only lives for 2 or 3 weeks.",
            "#351 Although Polar Bears have white, fluffy fur, their skin is actually black.",
            "#352 With their light body weight and blunt claws, cheetahs are not well designed to protect themselves or their prey. When a larger or more aggressive animal approaches a cheetah in the wild, it will give up its catch to avoid a fight.",
            "#353 Tigers are good swimmers and can swim up to 6 kilometres.",
            "#354 Parrots will selflessly help each other out.",
            "#355 Dolphins have names for each other.",
            "#356 Cats recognize their own name but choose not to respond.",
            "#357 While pandas sometimes eat fish or small animals, 99% of their diet is bamboo.",
            "#358 Instead of drinking water, frogs soak it into their body through their skin.",
            "#359 Giraffes have no vocal chords.",
            "#360 It takes a sloth two weeks to digest its food.",
            "#361 A baby rabbit is called a kit, a female is called a doe and a male is called a buck.",
            "#362 While they grow, reindeer antlers have a velvety covering. When the antlers are fully grown, the ‘velvet’ is shed and rubs away.",
            "#363 The world's oldest known breed of domesticated dog dates back to 329 BC.",
            "#364 Frogs breathe through their nostrils while also absorbing about half the air they need through their skin.",
            "#365 Some snails have hairy shells.",
            "#366 Cats and humans have been associated for nearly 10000 years.",
            "#367 Rabbits and parrots can see behind themselves without even moving their heads!",
            "#368 Toucans Curl Into A Little Tiny Ball When They Sleep",
            "#369 There are more tigers held privately as pets than there are in the wild.",
            "#370 Did you know that a great white shark can have 20,000 teeth throughout its life? A shark has rows of teeth, organized one after another, so when it loses a tooth, it can be replaced very quickly.",
            "#371 The Sun Bear has the longest tongue of all bear species – 8 to 10 inches long. If the Sun Bear is grabbed or bitten around its head, it can turn around inside the wrinkly skin on its head and bite the predator.",
            "#372 Giant Arctic jellyfish have tentacles that can reach over 36 metres in length.",
            "#373 Unlike other species of shark, the great white is warm-blooded. Although the great white does not keep a constant body temperature, it needs to eat a lot of meat in order to be able to regulate its temperature.",
            "#374 When lions breed with tigers the resulting hybrids are known as ligers and tigons. There are also lion and leopard hybrids known as leopons and lion and jaguar hybrids known as jaglions",
            "#375 Female leopards give birth to a little of two or three cubs at a time. By the time a cub is two years old it will leave the company of its mother and live on their own.",
            "#376 A flea can jump distances 200 times their body length. ",
            "#377 Only male toads croak.",
            "#378 Cowbirds use secret passwords to teach their young.",
            "#379 A group of ferrets is called a business.",
            "#380 A cat version of the corgi exits: the munchkin cat.",
            "#381 Squirrels Adopt Other Baby Squirrels If They’re Orphaned",
            "#382 The name for a baby lion is a cub, whelp or lionet.",
            "#383 More than half of all pigs in the world are kept by farmers in China.",
            "#384 Cheetahs are smaller than other members of the big cat family, weighing only 45 – 60 kilograms.",
            "#385 Dogs perform many useful tasks for humans including hunting, farm work and security as well as assisting those with disabilities such as the blind.",
            "#386 Rats laugh when tickled.",
            "#387 The largest land based mammals on Earth are elephants.",
            "#388 Rats are mainly nocturnal and live underground. Although they vastly outnumber humans, we rarely see them. They are experts at staying out of sight!",
            "#389 Cats have powerful night vision, allowing them to see at light levels six times lower than what a human needs in order to see.",
            "#390 Bottlenose dolphins are even more right-handed than humans.",
            "#391 Some people believe that the bones and whiskers of leopards can heal sick people. Many leopards are killed each year for their fur and body parts and this is one reason why the leopard is an endangered animal.",
            "#392 Moray eels have a second pair of \"Alien-style\" jaws.",
            "#393 A baby whale is called a calf. Whales form groups to look after calves and feed together. These groups are often made up of all female or all male whales.",
            "#394 Goldfish not only listen to music, but they also can distinguish one composer from another.",
            "#395 According to a tradition in Romania, farmers try to talk to their animals talk on New Year’s Day. They believe that talking to the animals brings good luck and better health.",
            "#396 Pigeons can do math.",
            "#397 The sun bear is also known as “Dog Bear” due to its glossy fur, short snout, and small size. They bark to scare away predators and to declare territory.",
            "#398 Sea otters hold hands when they sleep to keep from drifting apart.",
            "#399 Nearly 10 percent of all of a cat's bones are in its tail.",
            "#400 There are three different species of elephant – the African Savannah elephant, the African Forest elephant and the Asian elephant.",
            "#401 Norway knighted a penguin.",
            "#402 Tigers are the largest wild cats in the world.",
            "#403 The hair that makes up a giraffes tail is about 10 times thicker than the average strand of human hair.",
            "#404 Curious to know how dolphins sleep? Then, here you go, dolphins sleep half awake. They keep one eye open while they consciously breathe and float on the water surface.",
            "#405 When human measures for intelligence are applied to other species, dolphins come second only to Homo sapiens in brainpower.",
            "#406 Moths experience love at first scent.",
            "#407 Sharks lay the biggest eggs in the world.",
            "#408 Dolphins Will Intentionally Play With Puffer Fish And Get Poked By The Spikes. The Poison In The Spikes Gets Them High.",
            "#409 Primitive crocodiles could gallop.",
            "#410 There are two types of elephant, the Asian elephant and the African elephant (although sometimes the African Elephant is split into two species, the African Forest Elephant and the African Bush Elephant).",
            "#411 The spur-winged goose's diet makes it poisonous.",
            "#412 Bonobos do it missionary-style. About 30 percent of their heterosexual couplings are face-to-face, belly-to-belly.",
            "#413 Baby koalas are fed poo by their parents after they are born, this helps them digest Eucalyptus leaves later in life.",
            "#414 Sweden has a rabbit show-jumping competition called Kaninhoppning.",
            "#415 Pufferfish can contain a tetrodoxin, a toxin that is up to 1,200 times more deadly than cyanide to humans. There is enough toxin in one pufferfish to kill 30 adult humans, and there is no known antidote.",
            "#416 A giraffe has seven bones in its neck, which is the same as a human has, but they are much larger.",
            "#417 Bees Make A “Whoop!” Sound When They Bump Into Each Other Or Are Startled.",
            "#418 Sometimes whales make navigation mistakes during migrations. Although they may have made the mistake days before, they don’t realise it until they becoming stranded.",
            "#419 Rabbits are very effective baby-makers! Mother rabbits are pregnant for between 28-31 days, giving birth to up to 14 baby rabbits – called kittens – in a single litter. There are over 45 million rabbits in the UK alone!",
            "#420 Bees Get Sleepy After Drinking Nectar And Occasionally Take Naps On Flowers.",
            "#421 An adult gorilla is about 1 meter tall to their shoulders when walking on all fours using their arms and their legs.",
            "#422 A cow gives nearly 200,000 glasses of milk in a lifetime.",
            "#423 Humpback whale songs spread like \"cultural ripples from one population to another.\"",
            "#424 Dolphins and whales were land animals that evolved back to the ocean.",
            "#425 Although a giraffe’s neck is 1.5 – 1.8 metres, it contains the same number of vertebrae at a human neck.",
            "#426 When Dogs Appear In Movies And TV, Sometimes They Have To Have Cgi Tails Because They Wag Too Much During The Scene.",
            "#427 Only half of the dolphin’s brain goes to sleep when asleep and the other half stays awake.",
            "#428 A cat's tail contains nearly 10 percent of all the bones in its body",
            "#429 Lions are very social compared to other cat species, often living in prides that feature females, offspring and a few adult males.",
            "#430 The wolf is the ancestor of all breeds of domestic dog. It is part of a group of animals called the wild dogs which also includes the dingo and the coyote.",
            "#431 Tigers can reach a length of up to 3.3 metres (11 feet) and weigh as much as 300 kilograms (660 pounds).",
            "#432 Both female and male African elephants have tusks but only the male Asian elephants have tusks. They use their tusks for digging and finding food.",
            "#433 There are 79 to 84 different species of whale. They came in many different shapes and sizes!",
            "#434 A study measuring the effects of music found that cows produce more milk when listening to soothing music. They produce the most when listening to R.E.M’s “Everybody Hurts.”",
            "#435 Wolves in the Arctic have to travel much longer distances than wolves in the forest to find food and will sometimes go for several days without eating.",
            "#436 The heaviest domestic cat on record is 21.297 kilograms (46 lb 15.2 oz).",
            "#437 Water bears can survive being in outer space!",
            "#438 In the wild, some reindeer travel more than 3000 miles in a single year.",
            "#439 An ostrich's eye is bigger than its brain.",
            "#440 When the female in a group of clownfish dies, the most dominant male turns into a female to replace her.",
            "#441 Long-time bird watcher and nature writer Candace Savage has observed that crows are so intelligent they can play pranks on each other.",
            "#442 Japanese Macaques make snowballs for fun.",
            "#443 A shark always has a row of smaller teeth developing behind its front teeth. Eventually the smaller teeth move forward, like a conveyor belt, and the front teeth fall out.",
            "#444 Sneezing with your eyes open is impossible.",
            "#445 When an ant gets too drunk, his fellow comrade will carry him back to the nest to sleep off the alcohol.",
            "#446 Both male and female pigeons produce a substance called crop milk to feed their chicks. Very few birds have this rare ability, just greater flamingos, emperor penguins, pigeons and doves.",
            "#447 Wombat poop is cube-shaped.",
            "#448 Frogs can freeze without dying.",
            "#449 Honey bees are fab flyers. They fly at a speed of around 25km per hour and beat their wings 200 times per second!",
            "#450 Rats’ long tails are used for balance and to keep themselves cool – they can direct some of their body heat out through them! They’re also great swimmers, able to hold their breath for several minutes.",
            "#451 Crows don't forget a single face they come across. And if they do not have a fond memory of you then they will certainly not forget your face as they will hold a grudge against you. Interestingly, this grudge is carried on by their next generation.",
            "#452 Dogs are four times more likely to bite or become aggressive when walked by a male. It’s thought that this stems from dogs’ ability to pick up on a walker’s emotions or aggression.",
            "#453 Wild chimps like to drink.",
            "#454 Killer Whales, also known as Orcas, are not whales at all, and are actually a type of dolphin. They are the largest breed of dolphins in existence.",
            "#455 Humans get a little taller in space because there is no gravity pulling down on them.",
            "#456 Female red kangaroo has three vaginas. When two kangaroos meet for the first time, they touch their nose and sniff each other. Males smell the urine of females to see if she is ready to mate.",
            "#457 Turtles belong to one of the oldest reptile groups in the world – beating snakes, crocodiles and alligators!",
            "#458 Leopards can be found in various places around the world – they live in Sub-Saharan Africa, northeast Africa, Central Asia, India and China.",
            "#459 A hippo can hold its breath for 4-5 minutes and can give birth under water. They rest in water to keep their body temperature down because they don’t have sweat glands.",
            "#460 White-tailed jackrabbits are the greatest land jumpers, having been recorded leaping an astonishing 21ft (6.4m) vertically.",
            "#461 Slow lorises are the only venomous primates.",
            "#462 Dogs Can Tell When You’re Due To Come Home By How Much Of Your Scent Is Remaining In The House",
            "#463 Shrimps hearts are in their heads.",
            "#464 Older cats can at times act aggressively towards kittens.",
            "#465 The shark is the only fish that can blink with both eyes.",
            "#466 Tiger cubs leave their mother when they are around 2 years of age.",
            "#467 A hippo can hold breathe for 4-5 minutes. Hippopotamuses give birth in water. They rest in water to keep their temperature down because they don’t have sweat glands.",
            "#468 A pet hamster can run up to 8 miles a night on a wheel.",
            "#469 Kangaroos hop because they can’t move their legs independently.",
            "#470 Pandas Are No Longer Considered An Endangered Species.",
            "#471 A rhinoceros' horn is made of hair.",
            "#472 The closest relatives to the elephant shrew are actually elephants, not shrews.",
            "#473 Gorillas are considered to be very intelligent animals. They are known for their use of tools and their varied communication. Some gorillas in captivity at a zoo have been taught to use sign language.",
            "#474 Orcas can learn to speak dolphin.",
            "#475 It is possible to hypnotize a frog by placing it on its back and gently stroking its stomach.",
            "#476 Adult bison are the largest land mammals in North America.",
            "#477 The Naked Mole-Rat can live in an almost zero oxygen atmosphere.",
            "#478 Elephants are the largest land-living mammal in the world.",
            "#479 When you see a cat, slowly blink at it. If it blinks back, the cat is content with you.",
            "#480 Before chicks hatch, they can communicate with each other and their mother through a system of sounds.",
            "#481 Koalas are found in the eucalyptus forests of eastern Australia. They have grey fur with a cream-coloured chest, and strong, clawed feet, perfect for living in the branches of trees!",
            "#482 Octopuses Make Cute Little Gardens By Collecting Stones And Shiny Things And Arranging Them In The Sand",
            "#483 Dogs have two different air passages, one for breathing and another for smelling. This allows them to store scents in their nose, even while they are exhaling!",
            "#484 There are over 500 million domestic cats in the world.",
            "#485 Frogs sleep with their eyes open. It's hard to sneak up on a frog. They can see in all directions at once. Frog's don’t drink water. They absorb it through their skin. Male frogs use croaking to attract female frogs.",
            "#486 Rats are medium-sized rodents with a long tail. A group of rats is called a ‘mischief’!",
            "#487 Getting in Music rhythms helps children grasp fractions.",
            "#488 Tigers are good swimmers!",
            "#489 Hippopotamus milk is pink.",
            "#490 Some sharks glow in the dark.",
            "#491 Rabbits perform an athletic leap, known as a ‘binky‘, when they’re happy — performing twists and kicks in mid air!",
            "#492 The giant squid has the largest eyes in the world.",
            "#493 Although the Stegosaurus dinosaur was over 9 metres long, its brain was only the size of a walnut.",
            "#494 Sharks kill an average of 12 people per year - humans kill 11,417 sharks per hour.",
            "#495 The mantis shrimp has the world's fastest punch.",
            "#496 Did you know that the giraffe has 35 teeth?  Of all the animal kingdom, its teeth are the most similar to that of the human being, both in number and form.",
            "#497 Baby elephants suck their trunks for comfort.",
            "#498 A gorilla can live for 40 – 50 years.",
            "#499 Little is known about the elusive Giant squid, however the largest squid ever found measured over 50 feet and weighed nearly a tonne.",
            "#500 Your Dog Actually Loves You, Not Just Because You Give Them Food And Walks",
            "#501 A chameleon’s tongue is at least as long as its body, but it can grab prey in a fraction of a second.",
            "#502 You can tell the age of a whale by looking at the wax plug in its ear. This plug in the ear has a pattern of layers when cut lengthwise that scientists can count to estimate the age of the whale.",
            "#503 As well as being a famous Looney Tunes character, the Tasmanian Devil is a real animal that is only found in the wild in Tasmania, Australia. It is the largest carnivorous marsupial in the world.",
            "#504 Female lions do 90 percent of the hunting.",
            "#505 Dogs have way fewer taste buds than humans.",
            "#506 It Takes Some Time For Baby Elephants To Get Control Of Their Trunks.",
            "#507 Hummingbirds are the only birds in the world that can fly sideways, backwards, up and down, and even hover in mid-air. They can beat their wings up to 200 times per second. They are famous for being the smallest birds in the world.",
            "#508 Snow leopards don't roar.",
            "#509 Sea otters are adept at using tools.",
            "#510 The horned lizard is able to shoot blood from it’s own eyes, up to a distance of 3 feet away. The rather bizarre and disgusting act is a defensive mechanism to confuse predators.",
            "#511 A grizzly bear's bite is strong enough to crush a bowling ball.",
            "#512 Wolves live and hunt in groups called a pack. A pack can range from two wolves to as many as 20 wolves depending on such factors as habitat and food supply. Most packs have one breeding pair of wolves, called the alpha pair, who lead the hunt.",
            "#513 Rats’ super-strong teeth never stop growing! They have to keep nibbling to wear them down – or eating would become impossible!",
            "#514 Vampire bat’s teeth are so sharp that its bite may not be felt at all. Their saliva dulls any pain, so a bat may drink its victim’s blood for up to 30 minutes.",
            "#515 The pangolin is able to roll up into an armour-plated ball, so lions can’t eat them."
    };

    AdView vBannerAdViewAnimalsFactListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_fact_list);

        vPreviousMenuAnimalsFactListActivity = findViewById(R.id.previousMenuImageViewAnimalsFactListActivity);
        vPreviousMenuAnimalsFactListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent angerIntentAnimalsFactListActivity = new Intent(AnimalsFactList.this, Category.class);
                startActivity(angerIntentAnimalsFactListActivity);
                MusicComponent.shouldPlayCategoryActivity = false;
                MusicComponent.shouldPlayAnimalsFactListActivity = true;

            }
        });

        for(i = 0; i <= 514; i++){
            vFactNumbersAnimalsFactListActivity[i] = "Fact #" + (1 + i);
        }

        for (j = 0; j <= 514; j++) {
            extraTextAnimalsFactListActivity[j] = "EXTRA_TEXT_ANIMALS_FACT_LIST_ACTIVITY" + j;
        }

        vFactNumberListViewAnimalsFactListActivity = findViewById(R.id.factNumberListViewAnimalsFactListActivity);

        AnimalsFactsAdapter animalsFactsAdapterAnimalsFactListActivity = new AnimalsFactsAdapter();
        vFactNumberListViewAnimalsFactListActivity.setAdapter(animalsFactsAdapterAnimalsFactListActivity);


        vFactNumberListViewAnimalsFactListActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AnimalsFactList.this,vFactNumbersAnimalsFactListActivity[position],Toast.LENGTH_SHORT).show();
                vFactNumberAnimalsFactListActivity = position;

                Intent animalsFactIntentAnimalsFactListActivity = new Intent(AnimalsFactList.this, AnimalsFact.class);

                for (k = 0; k <= 514; k++) {
                    animalsFactIntentAnimalsFactListActivity.putExtra(extraTextAnimalsFactListActivity[k], animalsFacts[k]);
                }

                animalsFactIntentAnimalsFactListActivity.putExtra(EXTRA_NUMBER_ANIMALS_FACT_LIST_ACTIVITY, vFactNumberAnimalsFactListActivity);
                startActivity(animalsFactIntentAnimalsFactListActivity);
                MusicComponent.shouldPlayAnimalsFactListActivity = true;
                MusicComponent.shouldPlayAnimalsFactActivity = false;

            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        vBannerAdViewAnimalsFactListActivity = findViewById(R.id.bannerAdViewAnimalsFactListActivity);
        AdRequest vBannerAdRequestAnimalsFactListActivity = new AdRequest.Builder().build();
        vBannerAdViewAnimalsFactListActivity.loadAd(vBannerAdRequestAnimalsFactListActivity);



    }



    class AnimalsFactsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return vFactNumbersAnimalsFactListActivity.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            view = View.inflate(AnimalsFactList.this, R.layout.animals_fact_list_layout, null);
            if (view == null) {
                view = View.inflate(AnimalsFactList.this,R.layout.animals_fact_list_layout, null);
            }

            TextView mTextView = view.findViewById(R.id.animalsFactListTextView);

            mTextView.setText(vFactNumbersAnimalsFactListActivity[position]);

            return view;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferencesAnimalsFactListActivity = getSharedPreferences("Background", Context.MODE_PRIVATE);
        int vSelectedColorAnimalsFactListActivity = sharedPreferencesAnimalsFactListActivity.getInt("integerSelectedColor", 0);
        ConstraintLayout vConstraintLayoutAnimalsFactListActivity = findViewById(R.id.constraintLayoutAnimalsFactListActivity);
        if (sharedPreferencesAnimalsFactListActivity.getInt("integerSelectedColor", Color.WHITE) == vSelectedColorAnimalsFactListActivity)
        {vConstraintLayoutAnimalsFactListActivity.setBackgroundColor(vSelectedColorAnimalsFactListActivity);}
        else
        {vConstraintLayoutAnimalsFactListActivity.setBackgroundColor(Color.parseColor("#FDD935"));}

        if(mediaPlayer == null) {

            MusicComponent.BackgroundMusic(AnimalsFactList.this, R.raw.bg_music);

        }

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();

        }

        mediaPlayer.start();
        MusicComponent.onCompletion(mediaPlayer);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!MusicComponent.shouldPlayAnimalsFactListActivity){

            mediaPlayer.pause();
            MusicComponent.shouldPlayCategoryActivity = false;
            MusicComponent.shouldPlayAnimalsFactListActivity = true;

        }

    }
}