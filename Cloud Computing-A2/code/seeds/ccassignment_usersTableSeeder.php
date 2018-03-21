<?php

use Illuminate\Database\Seeder;

class ccassignment_usersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $faker = Faker\Factory::create();

        $limit = 19999;

        for ($i = 0; $i < $limit; $i++) {
            $name=$faker->userName;
            DB::table('ccassignment_users')->insert([ //,

                'id'=>$faker->unique()->numberBetween(13,$limit),
                'user_login'=>$name,
                'user_pass'=>$faker->password,
                'user_nicename'=>$name,
                'user_email' => $faker->unique()->email,
                'user_registered'=>$faker->dateTimeThisCentury,
                'user_status'=>0,
                'display_name'=>$name,

            ]);
        }
    }
}
