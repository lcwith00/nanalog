import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';


class SideBar extends Component {
    constructor(props) {
      super(props)
    }


    render() {
        return (
            <div className="side">
                <ul>
                    <li>
                        <Link to="/MonthlyPage" href="#">
                            <img src={require('../../images/btn-month.svg')}className="month"/>
                        </Link>
                    </li>
                    <li>
                        <Link to="/WeeklyPage" href="#">
                            <img src={require('../../images/btn-week.svg')} className="week"/>
                        </Link>
                    </li>
                </ul>
            </div>

        )
    };

}

export default SideBar;
