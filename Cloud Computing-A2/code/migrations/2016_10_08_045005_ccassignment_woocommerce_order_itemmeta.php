<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CcassignmentWoocommerceOrderItemmeta extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ccassignment_usermeta', function (Blueprint $table) {
            $table->increments('meta_id');
            $table->bigInteger('order_item_id');
            $table->string('meta_key');
            $table->longText('meta_value');

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
