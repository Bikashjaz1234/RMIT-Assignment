<?php

use Illuminate\Database\Seeder;

class ccassignment_woocommerce_order_itemsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $faker = Faker\Factory::create();
        $orderid=2883;
        $limit = 25000;

        $limit1 = 0;
        $limit2 = 2*$limit;
        $limit3 = 2*$limit2;

        for ($i = $limit1; $i < $limit; $i++) {
            DB::table('ccassignment_woocommerce_order_items')->insert([ //,
//change item id, change name,change limit 1 limit.
//                'order_item_id'=> 2,
                'order_item_name'=>'Cloud Services - Personal',
                'order_item_type'=>'line_item',
                'order_id'=>$orderid,

            ]);
            $orderid=$orderid+1;
        }

        for ($i = $limit; $i < $limit2; $i++) {
            DB::table('ccassignment_woocommerce_order_items')->insert([ //,
//change item id, change name,change limit 1 limit.
//                'order_item_id'=> 3,
                'order_item_name'=>'Cloud Services - Enterprise',
                'order_item_type'=>'line_item',
                'order_id'=>$orderid,

            ]);
            $orderid=$orderid+1;
        }

        for ($i = $limit2; $i < $limit3; $i++) {
            DB::table('ccassignment_woocommerce_order_items')->insert([ //,
//change item id, change name,change limit 1 limit.
//                'order_item_id'=> 4,
                'order_item_name'=>'Cloud Services - Business',
                'order_item_type'=>'line_item',
                'order_id'=>$orderid,

            ]);
            $orderid=$orderid+1;
        }
    }
}
