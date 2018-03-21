<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CcassignmentWoocommerceOrderItems extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ccassignment_usermeta', function (Blueprint $table) {
            $table->increments('order_item_id');
            $table->longText('order_item_name');
            $table->string('order_item_type');
            $table->bigInteger('order_id');

        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        //
    }
}
