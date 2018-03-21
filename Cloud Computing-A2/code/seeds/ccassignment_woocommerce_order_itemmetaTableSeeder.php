<?php

use Illuminate\Database\Seeder;

class ccassignment_woocommerce_order_itemmetaTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {

        $faker = Faker\Factory::create();
//change i
        $limit = 99999;

        for ($i = 10030; $i < ($i+$limit); $i++) {
            $price=$faker->numberBetween(10,500);
            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//Qty
                'order_item_id'=> $i,
                'meta_key'=>'_qty',
                'meta_value'=>$faker->numberBetween(1,100),

            ]);




            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//_product_id
                'order_item_id'=> $i,
                'meta_key'=>'_product_id',
                'meta_value'=>$faker->numberBetween(1,4*$limit),

            ]);



            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//_line_subtotal
                'order_item_id'=> $i,
                'meta_key'=>'_line_subtotal',
                'meta_value'=>$price,

            ]);




            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//_line_total
                'order_item_id'=> $i,
                'meta_key'=>'_line_total',
                'meta_value'=>$price,

            ]);






            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//_tax_class
                'order_item_id'=> $i,
                'meta_key'=>'_tax_class',


            ]);


            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//_variation_id
                'order_item_id'=> $i,
                'meta_key'=>'_variation_id',


            ]);


            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//_variation_id
                'order_item_id'=> $i,
                'meta_key'=>'_line_subtotal_tax',
                'meta_value'=>'0',

            ]);


            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//_line_tax
                'order_item_id'=> $i,
                'meta_key'=>'_line_tax',
                'meta_value'=>'0',

            ]);

            DB::table('ccassignment_woocommerce_order_itemmeta')->insert([ //,
//_line_tax
                'order_item_id'=> $i,
                'meta_key'=>'_line_tax_data',
                'meta_value'=>'a:2:{s:5:"total";a:0:{}s:8:"subtotal";a:0:{}}',

            ]);



        }

    }
}
