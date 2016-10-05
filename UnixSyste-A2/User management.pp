#Unix System Application Program Assignment 2
#class usap_a2 for this assignment


class usap_a2 {
  notify { 'usap_a2 activated':
  }
  
  group { 'sysadmin':
    ensure => present,
    system => true
  }
  
  group { 'car':
    ensure => present,
  }
  
  user { 'becca':
    ensure   => present,
    home     => '/home/becca',
    groups   => ['sysadmin', 'car'],
    password => 'rmit',
    uid      => 10014987,
    shell    => '/bin/bash',
  }
}