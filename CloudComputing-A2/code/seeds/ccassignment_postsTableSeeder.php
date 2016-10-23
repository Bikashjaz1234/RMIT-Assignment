<?php

use Illuminate\Database\Seeder;

class ccassignment_postsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $faker = Faker\Factory::create();

        $limit = 99999;

       for ($i = 0; $i < $limit; $i++) {
            $date=$faker->dateTimeThisCentury;
            DB::table('ccassignment_posts')->insert([ //,

                'post_author'=>1,
                'post_date_gmt' => $date,
                'post_date'=>$date,
                'post_title'=>'Order &ndash;Octoober ',
                'post_status'=>'wc-completed',
                'comment_status'=>'closed',
                'ping_status'=>'closed',
                'post_parent'=>0,
                'menu_order'=>0,
                'post_type'=>'shop_order'

            ]);
        }
    }
}
