<?php

use Illuminate\Database\Seeder;

class ccassignment_usermetaTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $faker = Faker\Factory::create();
//        The number of USERS want to insert
        $limit = 9999;

        for ($i = 13; $i < $limit; $i++) {
            $umate_id = $faker->unique()->numberBetween(13, $limit);
//          Pop all rows with sample data for a Certain USER

//FirstName:
            DB::table('ccassignment_usermeta')->insert([ //,

                    'user_id' => $umate_id,
                    'meta_key'=> 'first_name',
                    'meta_value'=>$faker->firstName,
            ]);

//LastName:
            DB::table('ccassignment_usermeta')->insert([ //,

                'user_id' => $umate_id,
                'meta_key'=> 'last_name',
                'meta_value'=>$faker->lastName,
            ]);

// City
            DB::table('ccassignment_usermeta')->insert([ //,

                'user_id' => $umate_id,
                'meta_key'=> 'billing_city',
                'meta_value'=>$faker->city,
            ]);
        }

    }
}
