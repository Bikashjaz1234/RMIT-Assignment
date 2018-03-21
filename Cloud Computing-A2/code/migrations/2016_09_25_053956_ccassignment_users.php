<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class ccassignmentUsers extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ccassignment_users', function (Blueprint $table) {
            $table->increments('id');
            $table->string('user_login');
            $table->string('user_pass');
            $table->string('user_nicename');
            $table->string('email')->unique();
            $table->string('user_url');
            $table->dateTime('user_registered');
            $table->string('user_activation_key');
            $table->integer('user_status');
            $table->string('display_name');
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
