package com.example.radiusagentassignment.common

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RxJavaSchedulerProvider(
    val mainScheduler: Scheduler,
    val computationScheduler: Scheduler,
    val ioScheduler: Scheduler
) {
    @Inject
    constructor() : this(Schedulers.single(), Schedulers.computation(), Schedulers.io())
}