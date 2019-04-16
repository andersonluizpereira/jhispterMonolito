import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'region',
                loadChildren: './region/region.module#ErpRegionModule'
            },
            {
                path: 'country',
                loadChildren: './country/country.module#ErpCountryModule'
            },
            {
                path: 'location',
                loadChildren: './location/location.module#ErpLocationModule'
            },
            {
                path: 'department',
                loadChildren: './department/department.module#ErpDepartmentModule'
            },
            {
                path: 'task',
                loadChildren: './task/task.module#ErpTaskModule'
            },
            {
                path: 'employee',
                loadChildren: './employee/employee.module#ErpEmployeeModule'
            },
            {
                path: 'job',
                loadChildren: './job/job.module#ErpJobModule'
            },
            {
                path: 'job-history',
                loadChildren: './job-history/job-history.module#ErpJobHistoryModule'
            },
            {
                path: 'address-dto',
                loadChildren: './address-dto/address-dto.module#ErpAddressDtoModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ErpEntityModule {}
